1)- SELECT `school`,COUNT(*) FROM `student`,class WHERE class.identifier=class_identifier GROUP BY `school`
2)- SELECT MAX(`Score`),MIN(`Score`),AVG(`Score`) FROM `student`,class WHERE class.identifier=class_identifier GROUP BY `school`
3)- SELECT MAX(`Score`),MIN(`Score`),AVG(`Score`) FROM `student`,class WHERE class.identifier=class_identifier
