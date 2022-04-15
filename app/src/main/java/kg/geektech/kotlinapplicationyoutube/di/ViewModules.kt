package kg.geektech.kotlinapplicationyoutube.di

import kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist.DetailsPlaylistViewModel
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistViewModel
import kg.geektech.kotlinapplicationyoutube.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModels: Module = module {

    viewModel { PlaylistViewModel(get()) }

    viewModel { DetailsPlaylistViewModel(get()) }

    viewModel { VideoViewModel(get()) }
}