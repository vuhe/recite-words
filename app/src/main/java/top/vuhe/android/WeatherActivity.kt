package top.vuhe.android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var map: Map<String, Weather>

    companion object {
        fun actionStart(context: Context, mode: String) {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra("mode", mode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 设置数据源
        when(intent.getStringExtra("mode")) {
            "xml" -> {
                map = getDataFromXml(resources.openRawResource(R.raw.weather1))
                binding.weatherTitle.text = "xml 数据源"
            }
            "json" ->  {
                map = getDataFromJson(resources.openRawResource(R.raw.weather))
                binding.weatherTitle.text = "json 数据源"
            }
        }
        // 默认北京天气
        setInfo("北京")

        binding.beijingBtn.setOnClickListener { setInfo("北京") }
        binding.shanghaiBtn.setOnClickListener { setInfo("上海") }
        binding.guangzhouBtn.setOnClickListener { setInfo("广州") }
    }

    private fun setInfo(name: String) {
        map[name]?.let {
            binding.city.text = it.name
            binding.weather.text = it.weather
            binding.temp.text = it.tmep
            binding.pm.text = "pm: ${it.pm}"
            binding.wind.text = "风力: ${it.wind}"
        }
    }
}