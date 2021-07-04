import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{ArrayType, StringType, StructType}


/**
 *  distinct() can be used to remove rows that have the same values on all columns
 *  dropDuplicates() can be used to remove rows that have the same values on multiple selected columns
 */
object distinct extends App{

  val spark = SparkSession.builder
    .master("local[1]")
    .appName("SparkByExamples.com")
    .getOrCreate()

  import spark.implicits._
  val simpleData = Seq(("James", "Sales", 3000),
    ("Michael", "Sales", 4600),
    ("Robert", "Sales", 4100),
    ("Maria", "Finance", 3000),
    ("James", "Sales", 3000),
    ("Scott", "Finance", 3300),
    ("Jen", "Finance", 3900),
    ("Jeff", "Marketing", 3000),
    ("Kumar", "Marketing", 2000),
    ("Saif", "Sales", 4100)
  )
  val df = simpleData.toDF("employee_name", "department", "salary")
  df.show()

  //Distinct all columns
  val distinctDF = df.distinct()
  println("Distinct count: "+distinctDF.count())
  distinctDF.show(false)

  val df2 = df.dropDuplicates()
  println("Distinct count: "+df2.count())
  df2.show(false)

  // remove duplicate rows based on multiple columns
  val dropDisDF = df.dropDuplicates("department","salary")
  println("Distinct count of department & salary : "+dropDisDF.count())
  dropDisDF.show(false)

}
