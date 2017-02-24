import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import org.junit.Test;
import swf.accel.io.TimeSeriesParser;
import swf.accel.model.AccelerationData;
import swf.calculator.accel.AccelMeanCalculator;
import swf.model.TimeSeries;
import swf.model.timeseries.Item;
import swf.transformer.MeanTransformer;

public class MeanTransformerTest {
  @Test
  public void testTransform() throws FileNotFoundException, IOException {
    LinkedList<Item<AccelerationData>> assertedAccelList = new LinkedList<Item<AccelerationData>>();
    assertedAccelList.add(new Item<AccelerationData>(1230, new AccelerationData(2, -25, 61)));
    assertedAccelList.add(new Item<AccelerationData>(1260, new AccelerationData(13, -10, 51)));
    assertedAccelList.add(new Item<AccelerationData>(1290, new AccelerationData(38, 14, 37)));
    assertedAccelList.add(new Item<AccelerationData>(1320, new AccelerationData(57, 42, 77)));
    assertedAccelList.add(new Item<AccelerationData>(1350, new AccelerationData(62, 58, 136)));
    assertedAccelList.add(new Item<AccelerationData>(1380, new AccelerationData(76, 85, 173)));
    assertedAccelList.add(new Item<AccelerationData>(1410, new AccelerationData(68, 99, 191)));
    LinkedList<Item<String>> assertedFlagsList = new LinkedList<Item<String>>();
    assertedFlagsList.add(new Item<String>(1320, "START 0"));
    TimeSeriesParser timeSeriesParser = new TimeSeriesParser();
    TimeSeries<AccelerationData> timeSeries =
        timeSeriesParser.parseTimeSeriesFromFile("build/resources/test/record2.txt");
    MeanTransformer<AccelerationData> meanTransformer =
        new MeanTransformer<AccelerationData>(50, 30, new AccelMeanCalculator());
    assertEquals(
        new TimeSeries<AccelerationData>(assertedAccelList, assertedFlagsList),
        meanTransformer.transform(timeSeries)
    );
  }
}
