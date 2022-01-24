package com.iconpln.kompor_induksi_Backend.service.serviceImpl;

import com.iconpln.kompor_induksi_Backend.assembler.DetailUserAssembler;
import com.iconpln.kompor_induksi_Backend.entity.DetailUserEntity;
import com.iconpln.kompor_induksi_Backend.entity.TimPenilaiDetail;
import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.DetailUserDto;
import com.iconpln.kompor_induksi_Backend.repository.DetailUserRepo;
import com.iconpln.kompor_induksi_Backend.repository.TimPenilaiDetailRepo;
import com.iconpln.kompor_induksi_Backend.repository.UnitRepo;
import com.iconpln.kompor_induksi_Backend.repository.UserRepo;
import com.iconpln.kompor_induksi_Backend.service.DetailUserService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.github.perplexhub.rsql.RSQLJPASupport.toSpecification;

@Service
public class DetailUserResponse implements DetailUserService {
    @Autowired
    DetailUserRepo repo;
    @Autowired
    DetailUserAssembler assembler;
    @Autowired
    UnitRepo unitRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TimPenilaiDetailRepo timPenilaiDetailRepo;

    @Override
    public List<DetailUserDto> findAll(@Nullable String filter, @Nullable Sort sort) {
        List<DetailUserEntity> list = StringUtils.isBlank(filter) ?
                repo.findAll(sort) : repo.findAll(toSpecification(filter), sort);
        return assembler.toDtos(list);
    }

    @Override
    public Page<DetailUserDto> findAll(@Nullable String filter, @Nullable Pageable pageable) {
        Page<DetailUserEntity> page = StringUtils.isBlank(filter) ?
                repo.findAll(pageable) : repo.findAll(toSpecification(filter), pageable);
        return new PageImpl<>(assembler.toDtos(page.getContent()), pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public Optional<DetailUserDto> save(@NotNull DetailUserDto dto) {
        return Optional.ofNullable(assembler.toDto(saveEntity(dto)));
    }

    @Override
    public Optional<DetailUserDto> getOne(@NotNull String s) {
        Optional<DetailUserEntity> optional = repo.findById(s);
        return optional.map(assembler::toDto);
    }

    @Override
    public Boolean delete(@NotNull String s) {
        Optional<DetailUserEntity> optionalDetailUser = repo.findById(s);
        if(optionalDetailUser.isPresent()){
            DetailUserEntity detailUserEntity = optionalDetailUser.get();
            detailUserEntity.setIsActive(false);
            detailUserEntity.setIsDeleted(true);
            repo.save(detailUserEntity);
            User user = detailUserEntity.getUser();
            user.setIsDeleted(true);
            user.setIsActive(false);
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public DetailUserEntity saveEntity(DetailUserDto dto) {
        DetailUserEntity detailUserEntity = assembler.toEntity(dto);
        if(dto.getId()!=null){
            detailUserEntity = repo.getOne(dto.getId());
            assembler.fromDto(dto, detailUserEntity);
            if(dto.getRole()!=null){
                User user = detailUserEntity.getUser();
                user.setRole(dto.getRole());
                userRepo.save(user);
            }
        }
        if(dto.getIdUnit()!=null){
            detailUserEntity.setUnit(unitRepo.getOne(dto.getIdUnit()));
        }
        if(dto.getIdUnit1()!=null){
            detailUserEntity.setUnit1(unitRepo.getOne(dto.getIdUnit1()));
            detailUserEntity.setUnit(detailUserEntity.getUnit1());
        }
        if(dto.getIdUnit2()!=null){
            detailUserEntity.setUnit2(unitRepo.getOne(dto.getIdUnit2()));
            detailUserEntity.setUnit(detailUserEntity.getUnit2());
        }
        if(dto.getIdUnit3()!=null){
            detailUserEntity.setUnit3(unitRepo.getOne(dto.getIdUnit3()));
            detailUserEntity.setUnit(detailUserEntity.getUnit3());
        }
        detailUserEntity = repo.save(detailUserEntity);
        if(dto.getId()!=null && dto.getIsActive()!=null){
            User user = detailUserEntity.getUser();
            user.setIsActive(dto.getIsActive());
            userRepo.save(user);
        }
        return detailUserEntity;
    }

    @Override
    public List<DetailUserDto> findByTimPenilai(@NotNull String idUnit, @NotNull LocalDate tanggalSelesai, @NotNull String idIndikator) {
        List<TimPenilaiDetail> timPenilaiDetailList = timPenilaiDetailRepo.findTimPenilaiDetailByTanggalIndikatorUnit(tanggalSelesai, idIndikator, idUnit);
        return timPenilaiDetailList.stream().map(TimPenilaiDetail::getPenilai).map(du -> assembler.toDto(du)).distinct().collect(Collectors.toList());
    }
}
