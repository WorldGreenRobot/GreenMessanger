package com.green.robot.greenmessanger.presenter.domain.usecase

import com.green.robot.greenmessanger.presenter.domain.entity.Message
import com.green.robot.greenmessanger.presenter.domain.entity.TextMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import javax.inject.Inject

class GetChatByIdChatUseCase @Inject constructor() {

    operator fun invoke(id: String): Flow<Result<List<Message>>>{
        return flow {
            emit(
                Result.success(
                    List(30) {
                        TextMessage(
                            id = it.toString(),
                            senderId = it.toString(),
                            receiverId = it.toString(),
                            content = "Message $it",
                            date = Calendar.getInstance().time,
                            isSender = it % 2 == 0,
                            isRead = it % 2 == 0
                        )
                    }
                )
            )
        }
    }
}