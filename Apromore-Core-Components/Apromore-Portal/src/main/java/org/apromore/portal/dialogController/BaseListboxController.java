/*
 * Copyright © 2009-2018 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.portal.dialogController;

import org.apromore.model.*;
import org.apromore.portal.common.UserSessionManager;
import org.apromore.portal.dialogController.workspaceOptions.AddFolderController;
import org.apromore.portal.dialogController.workspaceOptions.RenameFolderController;
import org.apromore.portal.exception.DialogException;
import org.apromore.portal.exception.ExceptionAllUsers;
import org.apromore.portal.exception.ExceptionDomains;
import org.apromore.portal.exception.ExceptionFormats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public abstract class BaseListboxController extends BaseController {

    private static final long serialVersionUID = -4693075788311730404L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseListboxController.class);

    private static final String ALERT = "Alert";
    private static final String FOLDER_DELETE = "Are you sure you want to delete selected Folder(s) and all it's contents?";
    private static final String PROCESS_DELETE = "Are you sure you want to delete selected Process(es)? If no version has been select the latest version will be removed.";
    private static final String FOLDER_PROCESS_DELETE = "Are you sure you want to delete selected Folders and Processes?";

    private final Listbox listBox;

    private final MainController mainController;

    private final Button refreshB;
    private final Button btnAddFolder;
    private final Button btnAddProcess;
    //private final Button btnGEDFolder;
    private final Button btnRenameFolder;
    private final Button btnRemoveFolder;
    private final Button btnSecurity;


    public BaseListboxController(MainController mainController, String componentId, ListitemRenderer itemRenderer) {
        super();
        setHflex("100%");
        setVflex("100%");

        this.mainController = mainController;
        listBox = createListbox(componentId);
        listBox.setPaginal((Paging) mainController.getFellow("pg"));
        listBox.setItemRenderer(itemRenderer);

        refreshB = (Button) mainController.getFellow("refreshB");
        btnAddFolder = (Button) mainController.getFellow("btnAddFolder");
        btnAddProcess = (Button) mainController.getFellow("btnAddProcess");
        //btnGEDFolder = (Button) mainController.getFellow("btnGEDFolder");
        btnRenameFolder = (Button) mainController.getFellow("btnRenameFolder");
        btnRemoveFolder = (Button) mainController.getFellow("btnRemoveFolder");
        btnSecurity = (Button) mainController.getFellow("btnSecurity");

        attachEvents();

        appendChild(listBox);
    }

    protected void attachEvents() {
        this.listBox.addEventListener("onKeyPress", new EventListener<KeyEvent>() {
            @Override
            public void onEvent(KeyEvent keyEvent) throws Exception {
                if ((keyEvent.isCtrlKey() && keyEvent.getKeyCode() == 65)) {
                    if (listBox.getSelectedCount() > 0) {
                        selectAll();
                    } else {
                        unselectAll();
                    }
                }
            }
        });

        this.refreshB.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                refreshContent();
            }
        });

        this.btnAddFolder.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                addFolder();
            }
        });

        this.btnAddProcess.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                addProcess();
            }
        });

        /*
        this.btnGEDFolder.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                changeGED();
            }
        });
        */

        this.btnRenameFolder.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                renameFolder();
            }
        });

        this.btnRemoveFolder.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                removeFolder();
            }
        });

        this.btnSecurity.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                security();
            }
        });
    }

    /**
     * Refresh the currently displayed content from any kind of data source
     */
    protected abstract void refreshContent();

    protected Listbox createListbox(String componentId) {
        return (Listbox) Executions.createComponents(componentId, getMainController(), null);
    }

    protected Listbox getListBox() {
        return listBox;
    }

    protected ListModelList getListModel() {
        return (ListModelList) listBox.getModel();
    }

    public void unselectAll() {
        getListBox().clearSelection();
    }

    public void selectAll() {
        getListBox().selectAll();
    }

    protected void addFolder() throws InterruptedException {
        getMainController().eraseMessage();
        try {
            new AddFolderController(getMainController());
        } catch (DialogException e) {
            Messagebox.show(e.getMessage(), "Attention", Messagebox.OK, Messagebox.ERROR);
        }
    }

    protected void addProcess() throws InterruptedException {
        getMainController().eraseMessage();
        try {
            new CreateProcessController(getMainController(), getMainController().getNativeTypes());
        } catch (SuspendNotAllowedException | InterruptedException e) {
            Messagebox.show(e.getMessage(), "Attention", Messagebox.OK, Messagebox.ERROR);
        } catch (ExceptionDomains e) {
            String message;
            if (e.getMessage() == null) {
                message = "Couldn't retrieve domains reference list.";
            } else {
                message = e.getMessage();
            }
            Messagebox.show(message, "Attention", Messagebox.OK, Messagebox.ERROR);
        } catch (ExceptionFormats e) {
            String message;
            if (e.getMessage() == null) {
                message = "Couldn't retrieve formats reference list.";
            } else {
                message = e.getMessage();
            }
            Messagebox.show(message, "Attention", Messagebox.OK, Messagebox.ERROR);
        } catch (ExceptionAllUsers e) {
            String message;
            if (e.getMessage() == null) {
                message = "Couldn't retrieve users reference list.";
            } else {
                message = e.getMessage();
            }
            Messagebox.show(message, "Attention", Messagebox.OK, Messagebox.ERROR);
        }
    }

    protected void renameFolder() throws InterruptedException {
        getMainController().eraseMessage();
        try {
            List<Integer> folderIds = UserSessionManager.getSelectedFolderIds();

            if (folderIds.size() == 1) {
                int selectedFolderId = folderIds.get(0);
                String selectedFolderName = "";
                List<FolderType> availableFolders = UserSessionManager.getCurrentFolder() == null || UserSessionManager.getCurrentFolder().getId() == 0 ? UserSessionManager.getTree() : UserSessionManager.getCurrentFolder().getFolders();
                for (FolderType folder : availableFolders) {
                    if (folder.getId() == selectedFolderId) {
                        selectedFolderName = folder.getFolderName();
                        break;
                    }
                }

                new RenameFolderController(getMainController(), folderIds.get(0), selectedFolderName);
            } else if (folderIds.size() > 1) {
                Messagebox.show("Only one item can be renamed at the time.", "Attention", Messagebox.OK, Messagebox.ERROR);
            } else {
                getMainController().getMenu().editData();
            }
        } catch (DialogException | ParseException | ExceptionAllUsers | ExceptionDomains e) {
            Messagebox.show(e.getMessage(), "Attention", Messagebox.OK, Messagebox.ERROR);
        }
    }

    protected void removeFolder() throws Exception {
        // See if the user has mixed folders and process models. we handle everything differently.
        ArrayList<FolderType> folders = getMainController().getMenu().getSelectedFolders();
        Map<SummaryType, List<VersionSummaryType>> elements =  getMainController().getSelectedElementsAndVersions();

        if (doesSelectionContainFoldersAndElements(folders, elements)) {
            showMessageFoldersAndElementsDelete(getMainController(), folders);
        } else {
            if (folders != null && !folders.isEmpty()) {
                showMessageFolderDelete(getMainController(), folders);
            } else if (elements != null && !elements.isEmpty()) {
                showMessageProcessesDelete(getMainController());
            } else {
                LOGGER.error("Nothing selected to delete?");
            }
        }
    }

    /* Show the message tailored to deleting one or more folders. */
    private void showMessageProcessesDelete(final MainController mainController) throws Exception {
        Messagebox.show(PROCESS_DELETE, ALERT, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            public void onEvent(Event evt) throws Exception {
                switch (((Integer) evt.getData())) {
                    case Messagebox.YES:
                        deleteElements(mainController);
                        mainController.loadWorkspace();
                        refreshContent();
                        break;
                    case Messagebox.NO:
                        break;
                }
            }
        });
    }

    /* Show the message tailored to deleting one or more folders. */
    private void showMessageFolderDelete(final MainController mainController, final ArrayList<FolderType> folders) throws Exception {
        Messagebox.show(FOLDER_DELETE, ALERT, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            public void onEvent(Event evt) throws Exception {
                switch (((Integer) evt.getData())) {
                    case Messagebox.YES:
                        deleteFolders(folders, mainController);
                        mainController.loadWorkspace();
                        refreshContent();
                        break;
                    case Messagebox.NO:
                        break;
                }
            }
        });
    }

    /* Show a message tailored to deleting a combo of folders and processes */
    private void showMessageFoldersAndElementsDelete(final MainController mainController, final ArrayList<FolderType> folders) throws Exception {
        Messagebox.show(FOLDER_PROCESS_DELETE, ALERT, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            public void onEvent(Event evt) throws Exception {
                switch (((Integer) evt.getData())) {
                    case Messagebox.YES:
                        deleteFolders(folders, mainController);
                        deleteElements(mainController);
                        mainController.loadWorkspace();
                        refreshContent();
                        break;
                    case Messagebox.NO:
                        break;
                }
            }
        });
    }

    /* Setup the Security controller. */
    protected void security() throws InterruptedException {
        getMainController().eraseMessage();
        try {
            new SecuritySetupController(getMainController());
        } catch (DialogException e) {
            Messagebox.show(e.getMessage(), "Attention", Messagebox.OK, Messagebox.ERROR);
        }
    }



    /* Removes all the selected processes, either the select version or the latest if no version is selected. */
    private void deleteElements(MainController mainController) throws Exception {
        mainController.getMenu().deleteSelectedElements();
    }

    /* Removes all the selected folders and the containing folders and processes. */
    private void deleteFolders(ArrayList<FolderType> folders, MainController mainController) {
        for (FolderType folderId : folders) {
            mainController.getService().deleteFolder(folderId.getId(), UserSessionManager.getCurrentUser().getUsername());
        }
        mainController.reloadSummaries();
    }

    public abstract void displaySummaries(List<FolderType> subFolders, SummariesType summaries, Boolean isQueryResult);

    public abstract SummaryListModel displaySummaries(List<FolderType> subFolders, boolean isQueryResult);

    /* Does the selection in the main detail list contain folders and processes. */
    private boolean doesSelectionContainFoldersAndElements(ArrayList<FolderType> folders, Map<SummaryType, List<VersionSummaryType>> elements) throws Exception {
        return (folders != null && !folders.isEmpty()) && (elements != null && !elements.isEmpty());
    }

    public MainController getMainController() {
        return mainController;
    }

    public class SummaryListModel extends ListModelList {
        final int pageSize = 10;  // TODO: ought to be externally configurable

        private SummariesType summaries, logSummaries;
        private int currentPageIndex = 0, currentLogPageIndex = 0;
        private List<FolderType> subFolders;

        /**
         * Constructor.
         *
         * @param subFolders  will be displayed before processes
         */
        SummaryListModel(List<FolderType> subFolders) {
            this.subFolders = subFolders;
            setMultiple(true);
        }

        public Object getElementAt(int index) {

            // Elements are always accessed in the following order: subfolders, then process models, then logs

            if (index < subFolders.size()) {
                return subFolders.get(index);  // subfolder
            } else {
                int processIndex = index - subFolders.size();
                SummariesType summaries = getSummaries(processIndex / pageSize);
                if (processIndex % pageSize < summaries.getSummary().size()) {
                    return summaries.getSummary().get(processIndex % pageSize);  // process model
                } else {
                    int logIndex = processIndex - summaries.getCount().intValue();
                    return getLogSummaries(logIndex / pageSize).getSummary().get(logIndex % pageSize);  // log
                }
            }
        }

        public int getSize() {
            return subFolders.size() + getSummaries(currentPageIndex).getCount().intValue() + getLogSummaries(currentLogPageIndex).getCount().intValue();
        }

        public int getTotalCount() {
            return getSummaries(currentPageIndex).getTotalCount().intValue();
        }

        private SummariesType getSummaries(int pageIndex) {
            if (summaries == null || currentPageIndex != pageIndex) {
                UserType user = UserSessionManager.getCurrentUser();
                FolderType currentFolder = UserSessionManager.getCurrentFolder();
                summaries = getService().getProcessSummaries(user.getId(), currentFolder == null ? 0 : currentFolder.getId(), pageIndex, pageSize);
                currentPageIndex = pageIndex;
            }
            return summaries;
        }

        private SummariesType getLogSummaries(int pageIndex) {
            if (logSummaries == null || currentLogPageIndex != pageIndex) {
                UserType user = UserSessionManager.getCurrentUser();
                FolderType currentFolder = UserSessionManager.getCurrentFolder();
                logSummaries = getService().getLogSummaries(user.getId(), currentFolder == null ? 0 : currentFolder.getId(), pageIndex, pageSize);
                currentLogPageIndex = pageIndex;
            }
            return logSummaries;
        }
    }
}
