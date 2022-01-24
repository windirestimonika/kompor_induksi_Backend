package com.iconpln.kompor_induksi_Backend.domain;

public class DatabaseConstant {
    //    master table
    public static final String TABLE_MASTER_KUALIFIKASI = "master_kualifikasi";
    public static final String TABLE_MASTER_VENDOR = "master_vendor";
    public static final String TABLE_MASTER_VENDOR_DETAIL = "master_vendor_detail";
    public static final String TABLE_MASTER_DETAIL_USER = "master_detail_user";
    public static final String TABLE_MASTER_UNIT = "master_unit";
    public static final String TABLE_MASTER_INDIKATOR = "master_indikator";
    public static final String TABLE_MASTER_KRITERIA = "master_kriteria";
    public static final String TABLE_MASTER_TIM_PENILAI = "master_tim_penilai";
    public static final String TABLE_MASTER_TIM_PENILAI_DETAIL = "master_tim_penilai_detail";
    public static final String TABLE_MASTER_PERIODE = "master_periode";
    public static final String TABLE_MASTER_SK = "master_sk";
    //    system table
    public static final String TABLE_SYSTEM_USER = "sys_user";
    public static final String TABLE_SYSTEM_MENU = "sys_menu";
    public static final String TABLE_SYSTEM_PERMISSION = "sys_permission";
    public static final String TABLE_SYSTEM_SETTINGS = "sys_settings";
    public static final String TABLE_SYSTEM_CRON_LOG = "sys_cron_log";
    public static final String TABLE_SYSTEM_FAILED_IMPORT_LOG = "sys_failed_import_log";
    //    transaksi table
    public static final String TABLE_TRX_PENGENDALIAN_KONTRAK = "trx_pengendalian_kontrak";
    public static final String TABLE_TRX_LINE_ITEM_KONTRAK = "trx_line_item_kontrak";
    public static final String TABLE_TRX_PENILAIAN = "trx_penilaian";
    public static final String TABLE_TRX_REKAP_VALIDASI_VENDOR = "trx_rekap_validasi_vendor";
    public static final String TABLE_TRX_KONFIRMASI_PLN_PEDULI = "trx_konfirmasi_pln_peduli";
    public static final String TABLE_TRX_PENILAIAN_PLN_PEDULI = "trx_penilaian_pln_peduli";
    //    master fk
    public static final String COLUMN_KUALIFIKASI = "id_kualifikasi";
    public static final String COLUMN_DETAIL_USER = "id_detail_user";
    public static final String COLUMN_UNIT = "id_unit";
    public static final String COLUMN_UNIT_1 = "id_unit_1";
    public static final String COLUMN_UNIT_2 = "id_unit_2";
    public static final String COLUMN_UNIT_3 = "id_unit_3";
    public static final String COLUMN_PARENT_UNIT = "id_parent_unit";
    public static final String COLUMN_INDIKATOR = "id_indikator";
    public static final String COLUMN_VENDOR = "id_vendor";
    public static final String COLUMN_PENGENDALIAN_KONTRAK = "id_pengendalian_kontrak";
    public static final String COLUMN_KRITERIA = "id_kriteria";
    public static final String COLUMN_PENILAI = "id_penilai";
    public static final String COLUMN_TIM_PENILAI = "id_tim_penilai";
    public static final String COLUMN_MENU = "id_menu";
    public static final String COLUMN_PERIODE = "id_periode";
    public static final String COLUMN_SK = "id_sk";
    public static final String COLUMN_REKAP_VALIDASI_VENDOR = "id_rekap_validasi_vendor";
}

