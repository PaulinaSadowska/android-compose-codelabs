/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.samples.crane.ui.captionTextStyle
import androidx.compose.ui.graphics.SolidColor

@Composable
fun CraneEditableUserInput(
        hint: String,
        caption: String? = null,
        @DrawableRes vectorImageId: Int? = null,
        onInputChanged: (String) -> Unit
) {
    val textState = rememberEditableUserInputState(hint = hint)

    CraneBaseUserInput(
            caption = caption,
            tintIcon = { !textState.isHint },
            showCaption = { !textState.isHint },
            vectorImageId = vectorImageId
    ) {
        BasicTextField(
                value = textState.text,
                onValueChange = { value ->
                    textState.text = value
                    if (!textState.isHint) onInputChanged(textState.text)
                },
                textStyle = if (textState.isHint) {
                    captionTextStyle.copy(color = LocalContentColor.current)
                } else {
                    MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
                },
                cursorBrush = SolidColor(LocalContentColor.current)
        )
    }
}

@Composable
fun rememberEditableUserInputState(hint: String): EditableUserInputState = remember(hint) {
    EditableUserInputState(hint, hint)
}

class EditableUserInputState(
        private val hint: String,
        initialText: String
) {
    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint
}