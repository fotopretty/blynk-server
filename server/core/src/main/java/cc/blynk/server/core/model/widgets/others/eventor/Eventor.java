package cc.blynk.server.core.model.widgets.others.eventor;

import cc.blynk.server.core.model.enums.PinType;
import cc.blynk.server.core.model.widgets.NoPinWidget;
import cc.blynk.server.core.model.widgets.Widget;
import cc.blynk.server.core.model.widgets.others.eventor.model.action.BaseAction;
import cc.blynk.server.core.model.widgets.others.eventor.model.action.SetPinAction;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 01.08.16.
 */
public class Eventor extends NoPinWidget {

    public Rule[] rules;

    public int deviceId;

    public Eventor() {
        this.width = 2;
        this.height = 1;
    }

    public Eventor(Rule[] rules) {
        this();
        this.rules = rules;
    }

    @Override
    public boolean updateIfSame(int deviceId, byte pin, PinType type, String value) {
        return false;
    }

    @Override
    public void updateIfSame(Widget widget) {
        //do nothing
    }

    @Override
    public boolean isSame(int deviceId, byte pin, PinType type) {
        return false;
    }

    @Override
    public String getJsonValue() {
        return null;
    }

    @Override
    public String getModeType() {
        return "out";
    }

    @Override
    public void append(StringBuilder sb, int deviceId) {
        if (rules != null && this.deviceId == deviceId) {
            for (Rule rule : rules) {
                if (rule.actions != null) {
                    for (BaseAction action : rule.actions) {
                        if (action instanceof SetPinAction) {
                            SetPinAction setPinActionAction = (SetPinAction) action;
                            if (setPinActionAction.dataStream != null) {
                                append(sb, setPinActionAction.dataStream.pin,
                                        setPinActionAction.dataStream.pinType, getModeType());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
