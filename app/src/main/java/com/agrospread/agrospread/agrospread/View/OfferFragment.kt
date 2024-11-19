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
import com.agrospread.agrospread.agrospread.View.Adapters.OfferAdapter
import com.agrospread.agrospread.agrospread.ViewModel.OfferViewModel


class OfferFragment : Fragment() {
    private lateinit var offerViewModel: OfferViewModel
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
        val view = inflater.inflate(R.layout.fragment_offer, container, false)

        // Set up ViewModel
        offerViewModel = ViewModelProvider(this).get(OfferViewModel::class.java)

        // RecyclerView setup
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        // Create an adapter and set it to the RecyclerView
        // Pass context and offerList to the adapter
        val offerAdapter = OfferAdapter(offerViewModel.offerList, requireContext())
        recyclerView.adapter = offerAdapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OfferFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
