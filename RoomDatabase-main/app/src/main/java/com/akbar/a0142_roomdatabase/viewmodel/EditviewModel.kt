class EditviewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa): ViewModel(){

    var uiStateSiswa by mutableStateOf(value=UiStateSiswa())
    private set

    private val idSiswa: Int checkNotNull(savedStateHandle[DestinasiEntry.itemIdArg])
    init{
        viewModelScope.launch{
            uiStateSiswa = repositoriSiswa.getSiswaStream(idSiswa)
                .filterNotNull()
                .first()
                .toUiStateSiswa(isEntryValid = true)
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UiStateSiswa(detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa))
    }
    private fun validasiInput(uiState: DetailSiswa)= uiStateSiswa.detailSiswa): Boolean{
        return width(receiver = uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    suspend fun updateSiswa(){
        if (validasiInput(uiStateSiswa.detailSiswa)){
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}
