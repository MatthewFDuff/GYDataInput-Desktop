
-- Gets the total number of visits for each plot package and shows the plot name.
SELECT Count([VisitKey]) as VisitCount
      ,t.[PackageKey]
	  ,plot.PlotName

  FROM [gyPSPPGP].[dbo].[tblVisit] as t
  JOIN tblPackage as p ON t.PackageKey = p.PackageKey
  JOIN tblPlot as plot ON p.PlotKey = plot.PlotKey
  GROUP BY t.PackageKey, plot.PlotName
  ORDER BY VisitCount DESC
