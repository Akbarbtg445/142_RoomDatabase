class DetailviewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa) : viewModel(){
    private val idSiswa: int checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemdArg])
            val uiDetailstate:StateFlow<DetailSiswaUiState> =
                repositoriSiswa.getSiswaStream(idSiswa)
                    .filterNotNull()
                    .map {
                        DetailSiswaUiState(detailSiswa = it.toDetailSiswa())
                    }.stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                        initialValue = DetailSiswaUiState()
                    )
    suspend fun deleteSiswa(){
        repositoriSiswa.deleteSiswa(uiStateSiswa.detailSiswa.toSiswa())
    }
}
)