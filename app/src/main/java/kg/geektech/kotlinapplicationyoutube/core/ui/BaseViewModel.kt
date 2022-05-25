package kg.geektech.kotlinapplicationyoutube.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() { //наследование от мьюмоделов
    val loading = MutableLiveData<Boolean>()
}