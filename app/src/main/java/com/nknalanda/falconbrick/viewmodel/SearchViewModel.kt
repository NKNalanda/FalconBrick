package com.nknalanda.falconbrick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SearchViewModel(
    private var appln: Application
): AndroidViewModel(appln)