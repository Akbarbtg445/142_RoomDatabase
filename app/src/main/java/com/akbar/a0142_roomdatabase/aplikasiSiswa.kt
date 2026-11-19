package com.akbar.a0142_roomdatabase

import android.app.Application
import com.akbar.a0142_roomdatabase.repositori.ContainerApp
import com.akbar.a0142_roomdatabase.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}
