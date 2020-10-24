package com.noha.settingapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class SettingsActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        // Instantiate the new Fragment
        val args = pref?.extras

        if (pref != null) {
            val fragment = supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                pref.fragment
            )
            fragment.arguments = args
            fragment.setTargetFragment(caller, 0)
            // Replace the existing Fragment with the new Fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit()
        }
        return true
    }


    /*ToDo 5) Inflate the hierarchy*/
    class SyncSettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.sync_preference, rootKey)
        }
    }

    class MessageSettingsFragment : PreferenceFragmentCompat() {
        
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.msg_preference, rootKey)

            /*ToDo: 7) Finding Preferences */
            val signaturePreference: EditTextPreference? = findPreference("signature")


            /*ToDO: 12) To listen for changes*/
            signaturePreference?.setOnPreferenceChangeListener { preference, newValue ->
                Toast.makeText(context, newValue.toString() , Toast.LENGTH_LONG).show()
                true
            }

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences, key: String ->

            }


            val replyPreference: ListPreference? = findPreference("reply")
            replyPreference?.setOnPreferenceChangeListener { preference, newValue ->

                // return new value
                Toast.makeText(context, newValue.toString() , Toast.LENGTH_LONG).show()
                true
            }

        }
    }

}