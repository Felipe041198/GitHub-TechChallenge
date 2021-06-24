package com.example.githubrepos.model

import android.database.Observable

interface ManageRepository {
    fun setRepo(id: Int): Observable<Repository>
    fun getRepo(id: Int): Observable<Repository>
}