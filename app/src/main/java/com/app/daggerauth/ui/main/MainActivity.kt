package com.app.daggerauth.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app.daggerauth.BaseActivity
import com.app.daggerauth.R
import com.app.daggerauth.ui.main.post.PostFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment() {

        supportFragmentManager.beginTransaction().replace(R.id.flContainer,PostFragment.getInstance()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.action_logout){
            sessionManager.logout()
        }
        return super.onOptionsItemSelected(item)
    }
}