package id.derysudrajat.rule

import com.android.SdkConstants
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

class ImageViewUsageDetector: Detector(), XmlScanner {

    override fun getApplicableElements(): Collection<String>? {
        return listOf(SdkConstants.IMAGE_VIEW)
    }

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            issue = ISSUE,
            location = context.getElementLocation(element),
            message = REPORT_MESSAGE,
            quickfixData = computeQuickFix()
        )
        super.visitElement(context, element)
    }

    private fun computeQuickFix(): LintFix {
        return LintFix.create()
            .replace().text(SdkConstants.IMAGE_VIEW)
            .with(APP_COMPACT_IMAGE)
            .build()
    }

    companion object {

        private const val REPORT_MESSAGE = "Jangan pake image view ini lagi ya oke"
        private const val APP_COMPACT_IMAGE = "androidx.appcompat.widget.AppCompatImageView"

        private val IMPLEMENTATION = Implementation(ImageViewUsageDetector::class.java, Scope.RESOURCE_FILE_SCOPE)

        val ISSUE = Issue.create(
            id = "OurImageViewIssueId", // Every issue has it's own unique id, also used when trying to suppress a lint
            briefDescription = "Use AppCompatImageView image view instead of a normal image view",
            explanation = "We have a business requirement, that we have to have a orange tint over every image, " +
                    "to avoid any code duplication, you are suggested to use our custom image view instead",
            category = Category.CORRECTNESS, // One out of several options like ICONS, SECURITY, COMPLIANCE, etc.
            priority = 10, // An integer value specifying priority from 1 to 10, with 10 being highest. Used when multiple issues are reported on same element
            severity = Severity.FATAL, // One of the following values FATAL, ERROR, WARNING, INFORMATIONAL, IGNORE
            implementation = IMPLEMENTATION // directing to the implementation of this issue, i.e. the detector class, used when registering this issue
        )
    }
}