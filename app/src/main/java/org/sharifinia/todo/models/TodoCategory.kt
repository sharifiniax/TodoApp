package org.sharifinia.todo.models


import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

import org.sharifinia.todo.ui.theme.Purple200
import org.sharifinia.todo.ui.theme.Purple500
import org.sharifinia.todo.ui.theme.Purple700
import org.sharifinia.todo.ui.theme.Teal200
import org.sharifinia.todo.ui.theme.Orange700
import org.sharifinia.todo.ui.theme.Orange500
import org.sharifinia.todo.ui.theme.Orange200
import org.sharifinia.todo.ui.theme.Green700
import org.sharifinia.todo.ui.theme.Green500
import org.sharifinia.todo.ui.theme.Green200
import org.sharifinia.todo.ui.theme.Red700
import org.sharifinia.todo.ui.theme.Red500
import org.sharifinia.todo.ui.theme.Red200
import org.sharifinia.todo.ui.theme.Blue700
import org.sharifinia.todo.ui.theme.Blue500
import org.sharifinia.todo.ui.theme.Blue200
import org.sharifinia.todo.ui.theme.Yellow700
import org.sharifinia.todo.ui.theme.Yellow500
import org.sharifinia.todo.ui.theme.Yellow200
import org.sharifinia.todo.ui.theme.Brown700
import org.sharifinia.todo.ui.theme.Brown500
import org.sharifinia.todo.ui.theme.Brown200


@Entity
data class TodoCategory(
    val name: String = "Inbox",
    val color: EColor = EColor.Purple
) {

    @PrimaryKey(autoGenerate = true)
    var categoryId: Int = 0

}

enum class EColor() {
    Purple,
    Teal,
    Orange,
    Green,
    Red,
    Blue,
    Yellow,
    Brown;

    companion object {
        fun convertColor(color: EColor,intensity:Int=500): Color {
            when (color) {

                Purple -> {
                    return when(intensity){
                        200->{
                            Purple200
                        }
                        500->{
                            Purple500
                        }
                        700->{
                            Purple700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Teal -> {
                    return when(intensity){
                        200->{
                            Purple200
                        }
                        500->{
                            Purple500
                        }
                        700->{
                            Purple700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Orange -> {
                    return when(intensity){
                        200->{
                            Orange200
                        }
                        500->{
                            Orange500
                        }
                        700->{
                            Orange700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Green -> {
                    return when(intensity){
                        200->{
                            Green200
                        }
                        500->{
                            Green500
                        }
                        700->{
                            Green700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Red -> {
                    return when(intensity){
                        200->{
                            Red200
                        }
                        500->{
                            Red500
                        }
                        700->{
                            Red700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Blue -> {
                    return when(intensity){
                        200->{
                            Blue200
                        }
                        500->{
                            Blue500
                        }
                        700->{
                            Blue700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Yellow -> {
                    return when(intensity){
                        200->{
                            Yellow200
                        }
                        500->{
                            Yellow500
                        }
                        700->{
                            Yellow700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                Brown -> {
                    return when(intensity){
                        200->{
                            Brown200
                        }
                        500->{
                            Brown500
                        }
                        700->{
                            Brown700
                        }
                        else -> {
                            throw IllegalArgumentException()
                        }
                    }
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }


        }

    }

}




