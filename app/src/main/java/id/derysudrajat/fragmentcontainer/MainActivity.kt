package id.derysudrajat.fragmentcontainer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.derysudrajat.fragmentcontainer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MenuListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFragment(0)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> setFragment(0)
                R.id.actionProduct -> setFragment(1)
                R.id.actionHistory -> setFragment(2)
            }
            true
        }
    }

    private fun setFragment(index: Int) {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerView.id, getCurrentFragment(index))
            .commit()
    }

    // view model -> data stable
    private val listOfFragment = listOf(
        HomeFragment.newInstance(this),
        ProductFragment.newInstance(),
        HistoryFragment.newInstance(this)
    )

    private fun getCurrentFragment(index: Int): Fragment = listOfFragment[index]

    override fun onMenuSelected(position: Int) {
        setFragment(position)
        binding.bottomNavigation.menu.getItem(position).isChecked = true
    }
}