import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import com.example.demo.IMyAidlInterface

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindService(Intent(this,TestService::class.java), object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                var testBinder = service as? TestService.TestBinder
                testBinder?.go()
            }
        },Service.BIND_AUTO_CREATE)
    }
}


class TestService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return object :IMyAidlInterface.Stub(){
            override fun basicTypes(
                anInt: Int,
                aLong: Long,
                aBoolean: Boolean,
                aFloat: Float,
                aDouble: Double,
                aString: String?
            ) {

            }

        }
    }


    class TestBinder: Binder(){
        fun go(){

        }
    }
}