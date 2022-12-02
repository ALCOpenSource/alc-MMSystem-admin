package com.peculiaruc.alc_mmsystem_admin.ui.dialogs.twoActionDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.DialogTwoActionBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseDialog
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve
import com.peculiaruc.alc_mmsystem_admin.utilities.setWidthPercent


/**
 * this represent any dialog that contain two action button.
 * */
class TwoActionDialogFragment : BaseDialog<DialogTwoActionBinding>() {

    override val layoutIdFragment: Int = R.layout.dialog_two_action
    override val viewModel: TwoActionDialogViewModel by viewModels()
    private val args: TwoActionDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(100)
        dialog?.window?.attributes?.gravity = Gravity.BOTTOM
        viewModel.setType(args.dialogType)
        onEvents()
    }

    private fun onEvents() {
        viewModel.dialogEvents.observe(viewLifecycleOwner, EventObserve {
            dialog?.dismiss()
        })
    }

}