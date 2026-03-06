package com.green.robot.greenmessanger.presenter.di

import com.green.robot.greenmessanger.presenter.data.impl.UserRepositoryImpl
import com.green.robot.greenmessanger.presenter.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(impl: UserRepositoryImpl): UserRepository
}