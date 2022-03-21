/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.autofillframework.app


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.android.autofillframework.R
import kotlinx.android.synthetic.main.login_activity.clear
import kotlinx.android.synthetic.main.login_activity.login
import kotlinx.android.synthetic.main.login_activity.passwordField
import kotlinx.android.synthetic.main.login_activity.usernameField
import kotlinx.android.synthetic.main.login_multiple_screen_1_activity.*


class MultipleScreensSignIn1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_multiple_screen_1_activity)
        next.setOnClickListener {
            if(usernameLabel.visibility == View.VISIBLE) {
                next()
            } else {
                login()
            }
        }
        clear.setOnClickListener { resetFields() }
    }

    private fun resetFields() {
        usernameField.setText("")
    }

    private fun setNextState(){
        usernameLabel.visibility = View.INVISIBLE
        usernameField.visibility = View.INVISIBLE
        passwordLabel.visibility = View.VISIBLE
        passwordField.visibility = View.VISIBLE
    }

    /**
     * Emulates a login action.
     */
    private fun next() {
        val username = usernameField.text.toString()
        if (!TextUtils.isEmpty(username)) {
            setNextState()
//            val intent = MultipleScreensSignIn2Activity.getStartActivityIntent(this@MultipleScreensSignIn1Activity, username)
//            startActivity(intent)
//            finish()
        } else {
            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Emulates a login action.
     */
    private fun login() {
        val username = usernameField.text.toString()
        val password = passwordField.text.toString()
        if (isValidCredentials(username, password)) {
            val intent = WelcomeActivity.getStartActivityIntent(this@MultipleScreensSignIn1Activity)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Dummy implementation for demo purposes. A real service should use secure mechanisms to
     * authenticate users.
     */
    fun isValidCredentials(username: String, password: String): Boolean {
        return username == password
    }

    companion object {

        fun getStartActivityIntent(context: Context): Intent {
            val intent = Intent(context, MultipleScreensSignIn1Activity::class.java)
            return intent
        }
    }
}