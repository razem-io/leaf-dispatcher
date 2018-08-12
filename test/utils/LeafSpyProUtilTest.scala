package utils

import org.scalatest.FunSuite
import test.TestCommons

class LeafSpyProUtilTest extends FunSuite{

  test("from Map To Object parser") {

    val data = LeafSpyProUtil.fromMap(TestCommons.leafSpyProDataMap)

    assert(data.isDefined)
  }
}
