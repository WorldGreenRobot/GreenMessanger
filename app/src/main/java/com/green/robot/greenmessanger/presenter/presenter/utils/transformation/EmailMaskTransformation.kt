package com.green.robot.greenmessanger.presenter.presenter.utils.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class EmailMaskTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val atIndex = originalText.indexOf('@')

        val transformed = if (atIndex > 1) {
            originalText.take(1) + "*".repeat(atIndex - 1) + originalText.substring(atIndex)
        } else {
            originalText
        }

        return TransformedText(
            androidx.compose.ui.text.AnnotatedString(transformed),
            OffsetMapping.Companion.Identity
        )
    }
}