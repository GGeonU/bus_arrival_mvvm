package com.example.dell.mvvm_bus_arrival.api

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
class ItemList {
    var arsId: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var stId: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var stNm: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var tmX: Double? = null
        @Element(required = false) get
        @Element(required = false) set

    var tmY: Double? = null
        @Element(required = false) get
        @Element(required = false) set

    var busRouteId: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var busRouteNm: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var busRouteType: Int? = null
        @Element(required = false) get
        @Element(required = false) set

    var seq: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var rtNm: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var arrmsg1: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var arrmsg2: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var routeType: Int? = null
        @Element(required = false) get
        @Element(required = false) set

    var firstTm: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var lastTm: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var term: String? = null
        @Element(required = false) get
        @Element(required = false) set

}

@Root(name = "msgBody", strict = false)
class MsgBody() {
    var itemList: List<ItemList>? = null
        @ElementList(inline = true, required = false) get
        @ElementList(inline = true, required = false) set
}

@Root(name = "msgHeader", strict = false)
class MsgHeader() {
    var headerCd: String? = null
        @Element get
        @Element set


    var headerMsg: String? = null
        @Element get
        @Element set
    var itemCount: String? = null
        @Element get
        @Element set
}

@Root(name = "ServiceResult", strict = false)
class ServiceResult {

    var comMsgHeader: String? = null
        @Element(required = false) get
        @Element(required = false) set

    var msgHeader: MsgHeader? = null
        @Element get
        @Element set

    var msgBody: MsgBody? = null
        @Element get
        @Element set
}