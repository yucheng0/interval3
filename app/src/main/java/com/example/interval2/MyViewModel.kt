package com.example.interval

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interval2.*
import kotlinx.coroutines.*
import java.util.*


class MyViewModel:ViewModel() {
    var s1 = MutableLiveData<Int>()
    var s2 = MutableLiveData<Int>()
    var s3 = MutableLiveData<Int>()
    var s4 = MutableLiveData<Int>()
    var s5 = false

    var num = MutableLiveData<Int>()
    var startBit = MutableLiveData<Boolean>()
    var poweronEnabled = MutableLiveData<Boolean>()
    var pageEnabled = MutableLiveData<Boolean>()
     var TOTALTIME = MutableLiveData<Int>()         //目前顯示值
     var LEFTTIME = MutableLiveData<Int>()
    var RIGHTTIME = MutableLiveData<Int>()
    var RESTTIME = MutableLiveData<Int>()
    var ACCTIME = MutableLiveData<Int>()
    var REP = MutableLiveData<Int>()
    var SELECTED = false
    var ACCTIMESTRING = MutableLiveData<String>()
    lateinit var job:Job

    init {
        num.value = 0
        startBit.value = false
        pageEnabled.value = false
        poweronEnabled.value = false
        TOTALTIME.value = TOTALTIMECONST   //設初值
        LEFTTIME.value = LEFTTIMECONST
        RIGHTTIME.value = RIGHTTIMECONST
        RESTTIME.value = RESTTIMECONST
        ACCTIME.value = ACCTIMECONST
        REP.value = REPCONST
        ACCTIMESTRING.value = "0:00"

        s1.value = TOTALTIMECONST
        s2.value = LEFTTIMECONST
        s3.value = RIGHTTIMECONST
        s4.value = RESTTIMECONST
    }

    lateinit var context: Context
    lateinit var mTTS:TextToSpeech

    // Text to Speech
    fun texttospeech(toSpeak:String) {

            mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
                if (status != TextToSpeech.ERROR) {
                    //if there is no error then set language
                    mTTS.language = Locale.UK
                    mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            })

    }

    //
    fun startProc(){
           if (startBit.value==false) {
           startBit.value = true
           num.value = num.value  //將所有資料更新一次
           }   else {Toast.makeText(context, "不要再按了", Toast.LENGTH_SHORT).show() }
         }

    fun pauseProc(){
        if (startBit.value == true) {
            startBit.value = false
            job.cancel()
        }
    }

    fun exitProc(){
        System.exit(0)
    }

    //協程處理
    fun initialize() {
         job = viewModelScope.launch {
   //         delay(1000)
             processBitmap()
            num.value = num.value?.plus(1)
    //        println("${num.value}")

        }
     }

}
    suspend fun processBitmap() = withContext(Dispatchers.IO) {
        delay(1000)
    }

