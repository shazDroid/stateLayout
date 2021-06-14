package com.shazdroid.statelayout

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.airbnb.lottie.LottieAnimationView

class StateLayout : LinearLayoutCompat {
    // constructors //
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context,
            attributeSet,
            defStyleAttr) {
        init(context, attributeSet, defStyleAttr)
    }

    // private variables //
    private val TAG = "SHAZ_VIEW"
    private lateinit var contentView: View
    private lateinit var inflater: LayoutInflater

    private lateinit var templateView: View
    private lateinit var loadingView: View
    private lateinit var errorView: View
    private lateinit var emptyListView: View

    /**
     * Initialize
     */
    private fun init(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) { // init variables //
        inflater = LayoutInflater.from(context)

        // init attributes //
        val typedArray = context.theme.obtainStyledAttributes(attributeSet,
                R.styleable.StateLayout,
                defStyleAttr,
                0)

        // get background color //
        val backgroundColor = typedArray.getColor(R.styleable.StateLayout_state_backgroundColor,
                resources.getColor(R.color.white))

        // get text color //
        val textColor = typedArray.getColor(R.styleable.StateLayout_state_textColor,
                resources.getColor(R.color.text_color_light))

        // init views //
        templateView = inflater.inflate(R.layout.template_view, this, false)
        templateView.setBackgroundColor(backgroundColor)

        // init loading view //
        loadingView = inflater.inflate(R.layout.loading_view, null, false)
        loadingView.findViewById<TextView>(R.id.loadingTV).setTextColor(textColor)

        // init error view //
        errorView = inflater.inflate(R.layout.error_view, null, false)
        errorView.findViewById<TextView>(R.id.errorTV).setTextColor(textColor)

        // init empty list view //
        emptyListView = inflater.inflate(R.layout.empty_list_view, null, false)
        emptyListView.findViewById<TextView>(R.id.emptyListTV).setTextColor(textColor)

        typedArray.recycle()
    }

    // init content view //
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 1) throw Exception("StateLayout can have only one direct child")
        contentView = getChildAt(0)
        addView(templateView)
    }

    // ****************************************************************************************** //

    // show error view //
    fun showEmptyView() {
        showViewType(State.EMPTY_LIST)
    }

    // show error view //
    fun showEmptyView(message: String) {
        emptyListView.findViewById<TextView>(R.id.emptyListTV).text = message
        showViewType(State.EMPTY_LIST)
    }

    // show error view //
    fun showEmptyView(message: String, emptyLottieRes: Int) {
        emptyListView.findViewById<TextView>(R.id.emptyListTV).text = message
        emptyListView.findViewById<LottieAnimationView>(R.id.emptyListLottieView)
            .setAnimation(emptyLottieRes)
        showViewType(State.EMPTY_LIST)
    }

    // ****************************************************************************************** //

    // show error view //
    fun showErrorView() {
        showViewType(State.ERROR)
    }

    // show error view with param//
    fun showErrorView(errorMessage: String) {
        errorView.findViewById<TextView>(R.id.errorTV).text = errorMessage
        showViewType(State.ERROR)
    }

    // show error view with param//
    fun showErrorView(errorMessage: String, errorLottieRes: Int = R.raw.error) {
        errorView.findViewById<TextView>(R.id.errorTV).text = errorMessage
        errorView.findViewById<LottieAnimationView>(R.id.errorLottieView)
            .setAnimation(errorLottieRes)
        showViewType(State.ERROR)
    }

    // show error view with param//
    fun showErrorView(
        errorMessage: String,
        errorLottieRes: Int = R.raw.error,
        retryClickListener: OnRetryClickListener
    ) {
        errorView.findViewById<TextView>(R.id.errorTV).text = errorMessage
        errorView.findViewById<LottieAnimationView>(R.id.errorLottieView)
            .setAnimation(errorLottieRes)
        showView(errorView.findViewById<TextView>(R.id.retryBtn))
        errorView.findViewById<TextView>(R.id.retryBtn).setOnClickListener {
            retryClickListener.onRetryClick()
        }
        showViewType(State.ERROR)
    }

    // show error view with param//
    fun showErrorView(errorMessage: String, retryClickListener: OnRetryClickListener) {
        errorView.findViewById<TextView>(R.id.errorTV).text = errorMessage
        showView(errorView.findViewById<TextView>(R.id.retryBtn))
        errorView.findViewById<TextView>(R.id.retryBtn).setOnClickListener {
            retryClickListener.onRetryClick()
        }
        showViewType(State.ERROR)
    }

    // show error view with param//
    fun showErrorView(
        errorMessage: String,
        errorLottieRes: Int = R.raw.error,
        retryButtonText: String,
        retryClickListener: OnRetryClickListener
    ) {
        errorView.findViewById<TextView>(R.id.errorTV).text = errorMessage
        errorView.findViewById<LottieAnimationView>(R.id.errorLottieView)
            .setAnimation(errorLottieRes)
        showView(errorView.findViewById<TextView>(R.id.retryBtn))
        errorView.findViewById<TextView>(R.id.retryBtn).text = retryButtonText
        errorView.findViewById<TextView>(R.id.retryBtn).setOnClickListener {
            retryClickListener.onRetryClick()
        }
        showViewType(State.ERROR)
    }

    // ****************************************************************************************** //

    // show loading view //
    fun showLoadingView() {
        showViewType(State.LOADING)
    }

    // show loading with param //
    fun showLoadingView(loadingMessage: String, loadingLottieRes: Int = R.raw.loading_truck) {
        loadingView.findViewById<TextView>(R.id.loadingTV).text = loadingMessage
        loadingView.findViewById<LottieAnimationView>(R.id.loadingLottieView)
            .setAnimation(loadingLottieRes)
        showViewType(State.LOADING)
    }

    // ****************************************************************************************** //

    // show content view //
    fun showContent() {
        showView(contentView)
        hideView(templateView)
    }

    // ****************************************************************************************** //

    // show view type //
    private fun showViewType(state: State) {
        hideView(contentView)
        showView(templateView)
        when (state) {
            State.LOADING -> {
                Log.d(TAG, "showViewType: LOADING")
                (templateView as LinearLayoutCompat).removeAllViews()
                (templateView as LinearLayoutCompat).addView(loadingView)
                invalidate()
            }
            State.ERROR -> {
                Log.d(TAG, "showViewType: ERROR")
                (templateView as LinearLayoutCompat).removeAllViews()
                (templateView as LinearLayoutCompat).addView(errorView)
                invalidate()
            }
            State.CONTENT -> {
                Log.d(TAG, "showViewType: CONTENT")
                showContent()
                invalidate()
            }

            State.EMPTY_LIST -> {
                Log.d(TAG, "showViewType: EMPTY LIST")
                (templateView as LinearLayoutCompat).removeAllViews()
                (templateView as LinearLayoutCompat).addView(emptyListView)
                invalidate()
            }
        }
    }

    // show custom //
    fun showCustom(stateOptions: StateOptions) {
        if (stateOptions.stateType == StateOptions.StateType.LOADING) {
            showLoadingView(stateOptions.loadingMessage, stateOptions.loadingLottieRes)
        } else {
            showErrorView(stateOptions.errorMessage, stateOptions.errorLottieRes)
        }
    }

    /**
     *  show view
     *  @param view view typeof View
     *  used to set visibility of view to VISIBLE
     */
    private fun showView(view: View) {
        view.alpha = 0f
        view.animate().alpha(1.0f).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    view.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }
            })
    }

    /**
     *  hide view
     *  @param view view typeof View
     *  used to set visibility of view to GONE
     */
    private fun hideView(view: View) {
        view.alpha = 1f
        view.animate().alpha(0f).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }
            })
    }

    private enum class State {
        LOADING, ERROR, CONTENT, EMPTY_LIST
    }

    interface OnRetryClickListener {
        fun onRetryClick()
    }
}