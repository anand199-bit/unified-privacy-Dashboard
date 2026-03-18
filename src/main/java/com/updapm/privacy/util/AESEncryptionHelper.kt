package com.updapm.privacy.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESEncryptionHelper {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private const val KEY_SIZE = 256
    
    // In production, store this securely using Android Keystore
    private val SECRET_KEY = generateKey()
    
    private fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM)
        keyGenerator.init(KEY_SIZE)
        return keyGenerator.generateKey()
    }
    
    fun encrypt(data: String): String {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val iv = ByteArray(16) { 0 } // In production, use random IV
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY, IvParameterSpec(iv))
            val encryptedBytes = cipher.doFinal(data.toByteArray())
            Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            "ENCRYPTION_ERROR"
        }
    }
    
    fun decrypt(encryptedData: String): String {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val iv = ByteArray(16) { 0 }
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY, IvParameterSpec(iv))
            val decodedBytes = Base64.decode(encryptedData, Base64.DEFAULT)
            val decryptedBytes = cipher.doFinal(decodedBytes)
            String(decryptedBytes)
        } catch (e: Exception) {
            "DECRYPTION_ERROR"
        }
    }
    
    fun isEncrypted(data: String): Boolean {
        return try {
            Base64.decode(data, Base64.DEFAULT)
            true
        } catch (e: Exception) {
            false
        }
    }
}
