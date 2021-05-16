package com.zcrain.ipcunit.domain

import java.io.Serializable

/**
 * Author：CWQ
 * Date：2021/5/16
 * Desc:
 */
class Student(var name: String, var age: Int) : Serializable {
    private val serialVersionUID = 1L

    override fun toString(): String {
        return "Student--name:$name,age:$age"
    }
}