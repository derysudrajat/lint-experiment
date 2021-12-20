package id.derysudrajat.rule

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class Registry: IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(ImageViewUsageDetector.ISSUE, OldThemeDetector.ISSUE)

    override val api: Int = CURRENT_API
}