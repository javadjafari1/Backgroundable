package ir.thatsmejavad.backgroundable.common

import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Usage of an extension in a Kotlin JUnit 5 test:
 *
 * class MyTest : CoroutineTest {
 *
 *   override lateinit var testScope: TestScope
 *
 *   override lateinit var dispatcher: TestDispatcher
 *
 * }
 */
@ExtendWith(TestCoroutineExtension::class)
interface CoroutineTest {
    var testScope: TestScope
    var dispatcher: TestDispatcher
}
