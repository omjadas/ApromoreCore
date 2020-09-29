/*-
 * #%L
 * This file is part of "Apromore Core".
 * %%
 * Copyright (C) 2018 - 2020 Apromore Pty Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package org.apromore.etlplugin.portal.viewModels;

import org.apache.commons.io.FilenameUtils;
import org.apromore.etlplugin.logic.services.FileHandlerService;
import org.apromore.etlplugin.logic.services.Transaction;
import org.apromore.etlplugin.logic.services.impl.IllegalFileTypeException;
import org.apromore.etlplugin.portal.ETLPluginPortal;
import org.apromore.etlplugin.portal.models.sidePanelModel.FileMetaData;
import org.apromore.etlplugin.portal.models.templateTableModel.TemplateTableBean;
import org.jooq.conf.ParamType;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.jooq.impl.DSL.*;

public class ButtonUploadController extends SelectorComposer<Button> {
    private static final String NULL_UPLOAD_MESSAGE = "No file is selected";
    private static final String ERROR = "Error";
    private static final Integer MAX_FILES_NUMBER = 10;
    private Boolean noFilesCheck;

    /**
     * Describes the actions taken when a file is uploaded.
     */
    @Listen("onUpload = #btnUpload")
    public void onFileUpload(UploadEvent event) {

        System.out.println("===> Click upload");
//        Media[] medias = Fileupload.get(MAX_FILES_NUMBER);
        Media[] medias = event.getMedias();

        if (medias != null) {
            System.out.println("===> Media size: " + medias.length);
        } else {
            System.out.println("===> Media is null");
        }

        if (medias != null && medias.length > 0 && medias.length <= 10) {
            String returnMessage;
            System.out.println("===> Media is present");
            Messagebox.show(
                    "Media recieved!",
                    ERROR,
                    Messagebox.OK,
                    Messagebox.ERROR);
        } else {
            System.out.println("===> Error! 4");
            Messagebox.show(
                    NULL_UPLOAD_MESSAGE,
                    ERROR,
                    Messagebox.OK,
                    Messagebox.ERROR);
        }
    }

    /**
     * Get no files uploaded boolean.
     *
     * @return boolean value for checking if no files are uploaded
     */
    public Boolean getNoFilesCheck() {
        return noFilesCheck;
    }
}
