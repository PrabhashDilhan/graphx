package graphx.sharedSparkContext;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 * Created by wso2 on 20/1/17.
 */
public class SoleSc {


    static JavaSparkContext sparkContext = new JavaSparkContext(
            new SparkConf().setAppName("SOME APP NAME").setMaster("local[2]").set("spark.executor.memory","1g")
    );

    static SQLContext sqlCtx = new SQLContext(sparkContext);
    public static DataFrame getVertexDataFrame(){
        StructType customSchema = new StructType(new StructField[] {
                new StructField("VERTEX", DataTypes.StringType, true, Metadata.empty())
        });
        DataFrame dataframe = sqlCtx.read()
                .format("com.databricks.spark.csv")
                .schema(customSchema)
                .option("header", "true")
                .load("/home/wso2/graph_data.csv");
        return dataframe;
    }
    public static DataFrame getEdgeDataFrame(){
        StructType customSchema = new StructType(new StructField[] {
                new StructField("SOURSE", DataTypes.StringType, true, Metadata.empty()),
                new StructField("DESTINATION", DataTypes.StringType, true, Metadata.empty())
        });
        DataFrame dataframe = sqlCtx.read()
                .format("com.databricks.spark.csv")
                .schema(customSchema)
                .option("header", "true")
                .load("/home/wso2/edge_data.csv");
        return dataframe;
    }

    DataFrame dataframe = sqlCtx.load("/home/wso2/graph_data.txt");
    public JavaSparkContext getSparkContext(){
        return this.sparkContext;
    }
}
