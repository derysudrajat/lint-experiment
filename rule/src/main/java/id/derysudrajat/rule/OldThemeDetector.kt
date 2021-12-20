package id.derysudrajat.rule

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

class OldThemeDetector : Detector(), XmlScanner {

    override fun getApplicableAttributes(): Collection<String>? {
        return listOf("android:theme", "style", "theme")
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        context.report(
            issue = ISSUE,
            location = context.getValueLocation(attribute),
            message = REPORT_MESSAGE,
            quickfixData = computeQuickFix()
        )
        super.visitAttribute(context, attribute)
    }

    private fun computeQuickFix(): LintFix {
        return LintFix.create()
            .replace().text(OLD_THEME)
            .with(NEW_THEME)
            .build()
    }

    companion object {

        private const val OLD_THEME = "@style/Theme.FragmentContainer"
        private const val NEW_THEME = "@style/NewTheme"
        private const val REPORT_MESSAGE = "Gaiiss kita punya tema baru ayok dipake ya"

        private val IMPLEMENTATION =
            Implementation(OldThemeDetector::class.java, Scope.RESOURCE_FILE_SCOPE)

        val ISSUE = Issue.create(
            id = "OldThemeIssue", // Every issue has it's own unique id, also used when trying to suppress a lint
            briefDescription = "Ayok ganti ya pake tema baru namanya la_regular jangan pake yang lama lagi",
            explanation = """
                Nah si la_regular ini bisa kamu ganti-ganti konfigurasinya jadi kedepannya bisa lebih flexible, keren tohhh? 
                Ada juga yang la_syariah khusus buat app syariah ya...
                Kalau nanti kedepannya ada tema-tema yang lain bisa banget kok, nanti tinggal ganti beberapa item ini
                
                <item name="brand_massive">@color/some_color_massive</item>
                <item name="brand_heavy">@color/some_color_heavy</item>
                <item name="brand_fair">@color/some_color_fair</item>
                <item name="brand_soft">@color/some_color_soft</item>
                <item name="brand_root">@color/some_color_root</item>
                
                gimana? simple kan... 
                
                Jalan-jalan ke Roma naik perahu
                Pulangnya bawa jangan lupa bawa hadiah
                Sekarang kita sudah punya tema yang baru
                So jangan lupa di coba yaaah...
            """.trimIndent(),
            category = Category.CORRECTNESS, // One out of several options like ICONS, SECURITY, COMPLIANCE, etc.
            priority = 10, // An integer value specifying priority from 1 to 10, with 10 being highest. Used when multiple issues are reported on same element
            severity = Severity.FATAL, // One of the following values FATAL, ERROR, WARNING, INFORMATIONAL, IGNORE
            implementation = IMPLEMENTATION // directing to the implementation of this issue, i.e. the detector class, used when registering this issue
        )
    }
}