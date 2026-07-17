import { LineChart } from '@mui/x-charts/LineChart';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import dayjs from "dayjs";

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

function convertData(data) {
    var yAxisArray = [];
    var xAxisArray = [];

    for(var i = 0, l = data.data.length; i < l; i++) {
        var obj = data.data[i];
        const date = dayjs(obj.date.toString());
        xAxisArray.push(date);
        yAxisArray.push(obj.finalvalue);
    }

    console.log(xAxisArray);
    console.log(yAxisArray);

    return [
        yAxisArray,
        xAxisArray
    ];
}

function EventsGraph(data) {
    var resArray = convertData(data);

    return (
        <>
        <ThemeProvider theme={darkTheme}>
        <CssBaseline />
            <LineChart
          xAxis={[{ 
            scaleType: 'time',
            dataKey: 'utc',
            data: resArray[1],
            valueFormatter: (v) => dayjs(v).format("DD/MM/YYYY HH:00"),
            }]}
          series={[
            {
              data: resArray[0], showMark: true,
            },
          ]}
          height={300}
        />
        </ThemeProvider>
        
        </>
    );
}

export default EventsGraph;