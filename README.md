# AndroidExplorer
This project contains some simple examples of using Android features

Themes are for hierarchies. Styles for a concrete view. Use ThemeOverlay to set different themes for different parts of the app (one part dark, another light(toolbar for example))

Резюме:
1) Темы резолвятся снизу вверх.
То есть если у нас Linear внутри которого Linear и у обоих выствлена тема, то значения присвоенные второму линеару будут иметь приорите, так как мы пойдем по всем родителям темы вниз, до самого начала, и значения родительской будут затираться (но могут и не затереться, если не переопредялились).
2) Всякие dialogTheme нужны для того, чтобы не выставлять каждый раз тему для диалогов и тп. Никакие значения из родительской не имеют приоритет при резолвинге.