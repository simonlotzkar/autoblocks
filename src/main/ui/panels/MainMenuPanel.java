package ui.panels;

/*
structure:
mainmenupanel (borderlayout)
	NORTH = titlePanel (figure it out)
	CENTER = mainpanel(borderlayout)
		NORTH = scrollpanesheader(gridlayout2x1)
			row1 = mainDisplayTitleLabel
			row2 = sideDisplayTitlePanel (cardlayout)
				card 1 = siderolloutputtitlepanel (gridlayout1x2)
					col1 = rolloutputlabel
					col2 = customrollbutton
				card2 = statblockTitleLabel
		CENTER = scrollPanesPanel (gridlayout1x2)
			col1 = mainDisplayScrollPanel (cardlayout)
				card1 = encounterLIST
				card2 = libraryLIST
				card3 = statBlockPanel
				card4 = statBlockCreationPanel
				card5 = characterPanel
				card6 = groupLIST
			col2 = sideDisplayScrollPane (cardlayout)
				card1 = encounterSideDisplayPanel (gridlayout2x1)
					row1 = rolloutputlogscrollpane [rolloutputlog]
					row2 = actionspanel (borderlayout)
						NORTH = actionspanel (gridlayout1x2)
							col1 = actionslabel
							col2 = customactionbutton
						CENTER = actionsscrollpane [actions]
						SOUTH = rollactionbutton
				card 2 = librarySideDisplayPanel (cardlayout)
					card1 = statBlockPanel
					card2 = statblockcreationPanel
		SOUTH = buttonpanel (cardlayout)
			card1 = libraryButtonPanel (gridlayout2x1)
				row1 = librarybuttonpanel0 (grid1x2)
					col1 = addselectedtoencounterbutton
					col2 = openstatblockbutton
				row2 = librarybuttonpanel1 (grid1x3)
					col1 = createnewstatblockbutton
					col2 = deleteselectedstatblocksbutton
					col3 = backbutton
			card2 = encounterButtonPanel (gridlayout3x1)
				row1 = encounterbuttonpanel0 (gridlayout1x2)
					col1 = opencharbutton
					col2 = opengroupbutton
				row2 = encounterbuttonpanel1 (gridlayout1x3)
					col1 = rollcheckbutton
					col2 = rollinitiativebutton
					col3 = edithpbutton
				row3 = encounterbuttonpanel2 (gridlayout1x3)
					col1 = editgroupbutton
					col2 = deletebutton
					col3 = backbutton
			card3 = characterButtonPanel (gridlayout2x1)
				row1 = encounterbuttonpanel1
				row2 = encounterbuttonpanel2
			card4 = groupButtonPanel (gridlayout3x1)
				row1 = opencharbutton
				row2 = encounterbuttonpanel1
				row3 = encounterbuttonpanel2
 */

public class MainMenuPanel {
}
