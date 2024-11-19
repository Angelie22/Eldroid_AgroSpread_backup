package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.View.Adapters.RecoAdapter
import com.agrospread.agrospread.agrospread.ViewModel.RecommendationViewModel

const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"

class Recommendation : Fragment() {
    private lateinit var recommendationViewModel: RecommendationViewModel
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)

        // Initialize ViewModel
        recommendationViewModel = ViewModelProvider(this).get(RecommendationViewModel::class.java)

        // RecyclerView setup
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observe the LiveData for recommendations
        recommendationViewModel.recommendations.observe(viewLifecycleOwner) { recommendations ->
            // Update the RecyclerView adapter with the latest data
            val adapter = RecoAdapter(recommendations, requireContext())
            recyclerView.adapter = adapter
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Recommendation().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}