package com.green.robot.greenmessanger.presenter.domain.usecase

import android.net.Uri
import com.green.robot.greenmessanger.presenter.domain.entity.chats.Chat
import com.green.robot.greenmessanger.presenter.domain.entity.chats.ChatData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Calendar
import javax.inject.Inject

class GetChatsUseCase @Inject constructor() {

    operator fun invoke(userId: String): Flow<Result<List<Chat>>>{
        return flowOf(
            Result.success(
                List(10) {
                    ChatData(
                        id = it.toString(),
                        title = "Chat $it",
                        lastMessage = "Last message $it",
                        date = Calendar.getInstance().time,
                        image = Uri.EMPTY,
                        countNotReadingMessage = it
                    )
                }
            )
        )
    }
}