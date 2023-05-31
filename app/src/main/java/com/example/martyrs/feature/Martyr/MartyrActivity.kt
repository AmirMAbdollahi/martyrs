package com.example.martyrs.feature.Martyr

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.BaseActivity
import com.example.martyrs.data.DataComment
import com.example.martyrs.feature.Martyr.comment.CommentListAdapter
import com.example.martyrs.services.ImageLoadingService
import com.example.martyrs.view.scroll.ObservableScrollViewCallbacks
import com.example.martyrs.view.scroll.ScrollState
import kotlinx.android.synthetic.main.activity_martyr.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class MartyrActivity : BaseActivity() {
    val martyrViewModel: MartyrViewModel by viewModel { parametersOf(intent.extras) }
    val imageLoadingService: ImageLoadingService by inject()
    val commentListAdapter: CommentListAdapter = CommentListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_martyr)

//        val imageView: ImageView = findViewById(R.id.dl_image)
//        val params: LayoutParams = imageView.getLayoutParams() as LinearLayout.LayoutParams
//        params.width = 120
//// existing height is ok as is, no need to edit it
//// existing height is ok as is, no need to edit it
//        imageView.setLayoutParams(params)

//        val params = martyrImage.layoutParams
//        params.width = 120
//        params.height = 120
//        martyrImage.layoutParams = params


        martyrViewModel.martyrLiveData.observe(this) {
            Timber.i(it.toString())
            imageLoadingService.loadImage(martyrImage, it.photoUrl)
            fullName.text = it.fullName
            fatherName.text = it.fatherFirstName
            birthDate.text = it.birthDate
            martyrDate.text = it.martyrDate
            age.text = it.age.toString()
            if (!burialPlace.text.isNullOrEmpty())
                burialPlace.text = it.burialPlace
            else burialPlace.text = getString(R.string.noInformation)
            if (!marriage.text.isNullOrEmpty())
                marriage.text = it.marriage
            else marriage.text = getString(R.string.noInformation)
            if (!bio.text.isNullOrEmpty())
                bio.text = it.bio
            else bio.text = getString(R.string.noInformation)
            if (!missionName.text.isNullOrEmpty())
                missionName.text = it.missionName
            else missionName.text = getString(R.string.noInformation)
            if (!martyrPlace.text.isNullOrEmpty())
                martyrPlace.text = it.martyrPlace
            else martyrPlace.text = getString(R.string.noInformation)
            if (!job.text.isNullOrEmpty())
                job.text = it.job
            else job.text = getString(R.string.noInformation)
            toolbarTitleTv.text = it.fullName
        }

        commentsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        commentsRv.adapter = commentListAdapter

        martyrViewModel.commentLiveData.observe(this) {
            Timber.i(it.toString())
            commentListAdapter.commentList = it as ArrayList<DataComment>
        }
        martyrViewModel.totalCountLiveData.observe(this) {
            totalCountComment.text = it.toString()
        }

    }
}