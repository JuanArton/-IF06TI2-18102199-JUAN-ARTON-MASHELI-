package com.juanArtonM_18102199.praktikum9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.juanArtonM_18102199.praktikum9.databinding.ActivitySettingPreferenceBinding

class SettingPreferenceActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val RESULT_CODE = 101

    }
    private lateinit var mSettingPreference: SettingPreference
    private lateinit var settingModel: SettingModel
    private lateinit var binding: ActivitySettingPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener(this)
        mSettingPreference = SettingPreference(this)
        settingModel = mSettingPreference.getSetting()
        showPreferenceInForm()
        supportActionBar?.title = getString(R.string.setting_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_save) {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val age = binding.edtAge.text.toString().trim()
            val phoneNo = binding.edtPhone.text.toString().trim()
            val univ = binding.edtUniv.text.toString().trim()
            val slogan = binding.edtSlogan.text.toString().trim()
            val isLoveMU = binding.rgLoveMu.checkedRadioButtonId == R.id.rb_yes
            if (name.isEmpty()) {
                binding.edtName.error = getString(R.string.field_required)
                return
            }
            if (email.isEmpty()) {
                binding.edtEmail.error = getString(R.string.field_required)
                return
            }
            if (!isValidEmail(email)) {
                binding.edtEmail.error = getString(R.string.email_is_not_valid)
                return
            }
            if (age.isEmpty()) {
                binding.edtAge.error = getString(R.string.field_required)
                return
            }
            if (phoneNo.isEmpty()) {
                binding.edtPhone.error = getString(R.string.field_required)
                return
            }
            if (!TextUtils.isDigitsOnly(phoneNo)) {
                binding.edtPhone.error = getString(R.string.field_digit_only)
                return
            }
            if (univ.isEmpty()) {
                binding.edtUniv.error = getString(R.string.field_required)
                return
            }
            if (slogan.isEmpty()) {
                binding.edtSlogan.error = getString(R.string.field_required)
                return
            }
            saveSetting(name, email, age, phoneNo, isLoveMU, univ, slogan)
            val resultIntent = Intent()
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showPreferenceInForm() {
        binding.edtName.setText(settingModel.name)
        binding.edtEmail.setText(settingModel.email)
        binding.edtAge.setText(settingModel.age.toString())
        binding.edtPhone.setText(settingModel.phoneNumber)
        binding.edtUniv.setText(settingModel.univ)
        binding.edtSlogan.setText(settingModel.slogan)
        if (settingModel.isDarkTheme) {
            binding.rbYes.isChecked = true
        } else {
            binding.rbNo.isChecked = true
        }
    }

    private fun saveSetting(name: String, email: String, age: String, phoneNo: String, isLoveMU: Boolean, univ: String, slogan: String) {
        val settingPreference = SettingPreference(this)
        settingModel.name = name
        settingModel.email = email
        settingModel.age = Integer.parseInt(age)
        settingModel.phoneNumber = phoneNo
        settingModel.isDarkTheme = isLoveMU
        settingModel.univ = univ
        settingModel.slogan = slogan
        settingPreference.setSetting(settingModel)
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }
}