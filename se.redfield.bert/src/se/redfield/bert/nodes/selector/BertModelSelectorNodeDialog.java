/*
 * Copyright (c) 2020 Redfield AB.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License, Version 3, as
 * published by the Free Software Foundation.
 *  
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package se.redfield.bert.nodes.selector;

import javax.swing.JComponent;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.port.PortObjectSpec;

import se.redfield.bert.setting.BertModelSelectorSettings;
import se.redfield.bert.setting.ui.BertModelSelectorEditor;

/**
 * Settings dialog for the {@link BertModelSelectorNodeModel} node.
 * 
 * @author Alexander Bondaletov
 *
 */
public class BertModelSelectorNodeDialog extends NodeDialogPane {

	private final BertModelSelectorSettings settings;
	private final BertModelSelectorEditor editor;

	/**
	 * Creates new instance
	 */
	public BertModelSelectorNodeDialog() {
		settings = new BertModelSelectorSettings();
		editor = new BertModelSelectorEditor(settings);

		addTab("Settings", editor);
		addTab("Advanced", createAdvancedTab());
	}

	private JComponent createAdvancedTab() {
		DialogComponentBoolean advancedMode = new DialogComponentBoolean(settings.getAdvancedModeEnabledModel(),
				"Enable Remote URL and Local path selection modes");
		return advancedMode.getComponentPanel();
	}

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings) throws InvalidSettingsException {
		this.settings.saveSettingsTo(settings);
	}

	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings, PortObjectSpec[] specs) throws NotConfigurableException {
		try {
			this.settings.loadSettingsFrom(settings);
		} catch (InvalidSettingsException e) {
			// ignore
		}
		editor.onSettingsLoaded();
	}
}
