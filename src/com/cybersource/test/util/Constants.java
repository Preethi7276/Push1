package com.cybersource.test.util;

public class Constants {
    
	public static final String MODULE_TYPE="module.type";
	public static final String UNIFIED_CHECKOUT = "Unified Checkout";
	public static final String BROWSER_TYPE="browser.type";
	public static final String BROWSER_ADVANCED_TEXT = "Your connection is not private";
	public static final String CREDIT_CARD = "CreditCard";
	public static final String GOOGLE_PAY = "GooglePay";
	public static final String VISA_CHECKOUT = "VisaCheckout";
	public static final String FRONT_OFFICE_URL = "front.office.url";
	public static final String FRONT_OFFICE_PRODUCT_NAVIGATE = "https://localhost/VAS4/?s=";
	public static final String FRONT_OFFICE_MY_ACCOUNT_URL = "front.office.myaccount.url";
	public static final String BACK_OFFICE_LOGIN_URL = "back.office.login.url";
	public static final String BACK_OFFICE_ADMIN_USERNAME = "back.office.admin.username.id";
	public static final String BACK_OFFICE_ADMIN_PASSWORD = "back.office.admin.password.id";
	public static final String BACK_OFFICE_LOGIN_SUBMIT = "back.office.login.submit.id";
	public static final String BACK_OFFICE_WOOCOMMERCE = "back.ofice.woocommerce.xpath";
	public static final String BACK_OFFICE_WC_ORDERS = "back.office.wc.orders.xpath";
	public static final String BACK_OFFICE_WC_SETTINGS = "back.office.wc.settings.xpath";
	public static final String BACK_OFFICE_SETTINGS_PAYMENT = "back.office.settings.payments.xpath";
	public static final String BACK_OFFICE_PAYMENT_METHOD = "back.office.payment.method";
	public static final String BACK_OFFICE_PAYMENT_MANAGE = "back.office.payment.manage";

	public static final String BACK_OFFICE_CC_ENABLE = "back.office.cc.enable.id";
	public static final String BACK_OFFICE_CC_TRANSACTION_TYPE = "back.office.cc.transaction.type.id";
	public static final String BACK_OFFICE_CC_PAYER_AUTH_ENABLE = "back.office.payer.auth.enable";
	public static final String BACK_OFFICE_CC_TOKENIZATION = "back.office.cc.tokenization.id";
	public static final String BACK_OFFICE_CC_TOKEN_PROFILE_ID = "back.office.cc.token.profile.id";
	public static final String BACK_OFFICE_CC_ENVIRONMENT = "back.office.cc.environment.id";
	public static final String BACK_OFFICE_CC_MERCHANT_ID = "back.office.cc.merchant.xpath";
	public static final String BACK_OFFICE_CC_MERCHANT_KEY_ID = "back.office.cc.merchant.key.xpath";
	public static final String BACK_OFFICE_CC_MERCHANT_SECRET_KEY = "back.office.cc.merchant.secret.key.xpath";
	public static final String BACK_OFFICE_CC_FM_ENABLE = "back.office.cc.fm.id";
	public static final String BACK_OFFICE_CC_FM_ENABLE_XPATH = "back.office.cc.fm.xpath";
	public static final String BACK_OFFICE_CC_DFP = "back.office.cc.dfp.id";
	public static final String BACK_OFFICE_CC_SAVE_BUTTON = "back.office.cc.save.button.xpath";
	public static final String GPAY_CONTAINER_BUTTON = "gpay.container.button";  
	public static final String FRONT_OFFICE_GOOGLE_LOGIN_USERNAME_TEXT_FIELD = "google.login.username.text.field";
	public static final String GOOGLE_PAY_LOGIN_USERNAME = "google.login.username";
	public static final String GOOGLE_PAY_LOGIN_USERNAME_NEXT_BTN = "google.login.username.next.btn";
	public static final String FRONT_OFFICE_GOOGLE_LOGIN_PASSWORD_TEXT_FIELD = "google.login.password.text.field";
	public static final String GOOGLE_PAY_LOGIN_PASSWORD = "google.login.password";
	public static final String GOOGLE_PAY_LOGIN_PASSWORD_NEXT_BTN = "google.login.password.next.btn";
	public static final String FRONT_OFFICE_GOOGLE_MODAL_WINDOW_CLICK = "google.pay.iframe.modal.click";
	public static final String GOOGLE_PAY_PAYMENTCONTINUE_BTN = "google.pay.paymentcontinue.btn";

	
	//Coupon
	public static final String FRONT_OFFICE_COUPON_LINK = "front.office.couponlink.class";
	public static final String FRONT_OFFICE_COUPON_BOX = "front.office.couponbox.id";
	public static final String FRONT_OFFICE_COUPON_APPLY_BUTTON = "front.office.couponapply.button.name";

	//Currencies
	public static final String BACK_OFFICE_CURRENCY_OPTIONS = "back.office.currency.options.xpath";
	public static final String BACK_OFFICE_CURRENCY = "back.office.currency.xpath";
	public static final String BACK_OFFICE_DECIMAL_PLACE = "back.office.decimalpoint.xpath";
	public static final String BACK_OFFICE_SAVE = "back.office.save.xpath";
	
	public static final String FRONT_OFFICE_MYACCOUNT_USERNAME = "front.office.myaccount.username.id";
	public static final String FRONT_OFFICE_MYACCOUNT_PASSWORD = "front.office.myaccount.password.id";
	public static final String FRONT_OFFICE_MYACCOUNT_LOGIN_BUTTON = "front.office.myaccount.login.submit.xpath";
	public static final String FRONT_OFFICE_FAILED_LOGOUT="front.office.failed.logout";
	public static final String FRONT_OFFICE_MYACCOUNT="front.office.myaccount";
	public static final String FRONT_OFFICE_MYACCOUNT_PAYMENT_METHOD = "front.office.myaccount.payment.method.xpath";
	public static final String FRONT_OFFICE_MYACCOUNT_ADD_NEW_PAYMENT_METHOD = "front.office.myaccount.add.payment.method.xpath";
	public static final String FRONT_OFFICE_MYACCOUNT_ADD_CARD = "front.office.myaccount.addCard.xpath";
	public static final String FRONT_OFFICE_MYACCOUNT_CARDS_TABLE_XPATH = "front.offie.myaccount.table.xpath";
	public static final String FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_PREFIX = "front.office.myaccount.delete.xpath.prefix";
	public static final String FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_SUFFIX = "front.office.myaccount.delete.xpath.suffix";
	public static final String FRONT_OFFICE_MYACCOUNT_MESSAGE = "front.office.myaccount.message.xpath";
	public static final String FRONT_OFFICE_MYACCOUNT_LOGOUT = "front.office.myaccount.logout.xpath";
	

	public static final String BACK_OFFICE_ORDERS_ID_PREFIX = "back.office.order.id.prefix";
	public static final String BACK_OFFICE_ORDERS_ID_SUFFIX = "back.office.order.id.suffix";
	public static final String BACK_OFFICE_ORDERS_CAPTURE_CHARGE = "back.office.order.capture.xpath";
	public static final String BACK_OFFICE_ORDERS_AUTH_REVERSAL = "back.office.refund.authReversal.xpath";
	public static final String BACK_OFFICE_REFUND_AMOUNT = "back.office.refund.amount.xpath";
	public static final String BACK_OFFICE_CANCEL_AMOUNT = "back.office.authreversal.amount.id";
	public static final String BACK_OFFICE_ORDERS_REFUND = "back.office.refund.xpath";
	public static final String BACK_OFFICE_ORDERS_REFUND_BOFA = "back.office.refund.bofa.xpath";
	public static final String BACK_OFFICE_ORDERS_REFUND_QUANTITY = "back.office.refund.quantity.xpath";
	public static final String BACK_OFFICE_ORDERS_REFUND_AMOUNT = "back.office.refund.amount.xpath";
	public static final String BACK_OFFICE_PRODUCT_TABLE_XPATH = "back.office.product.table.xpath";
	public static final String BACK_OFFICE_REFUND_QUANTITY_PREFIX = "back.office.partial.refund.quantity.prefix";
	public static final String BACK_OFFICE_REFUND_QUANTITY_SUFFIX = "back.office.partial.refund.quantity.suffix";
	public static final String BACK_OFFICE_REFUND_AMOUNT_PREFIX = "back.office.partial.refund.amount.prefix";
	public static final String BACK_OFFICE_REFUND_AMOUNT_SUFFIX = "back.office.partial.refund.amount.suffix";

	public static final String BACK_OFFICE_ORDERS_TRANSACTION_ID = "back.office.order.transactionId.xpath";
	public static final String BACK_OFFICE_ORDERS_REFUND_TRANSACTION_ID="back.office.order.refund.transactionId.xpath";
	public static final String BACK_OFFICE_ORDERS_STATUS = "back.office.order.status.xpath";
	public static final String BACK_OFFICE_LOGOUT_HOVER = "back.office.logout.hover.xpath";
	public static final String BACK_OFFICE_LOGOUT = "back.office.logout.xpath";

	public static final String BACK_OFFICE_STATUS_ON_HOLD = "On hold";
	public static final String BACK_OFFICE_STATUS_PROCESSING = "Processing";
	public static final String BACK_OFFICE_STATUS_CANCELLED = "Cancelled";
	public static final String BACK_OFFICE_STATUS_REFUNDED = "Refunded";
	
	//TAXES
	public static final String BACK_OFFICE_TAX_ENABLE_CHECKBOX = "back.office.tax.enable.checkbox";
	public static final String BACK_OFFICE_TAX_TAB_NAVIGATE = "back.office.tax.tab.navigate";
	public static final String BACK_OFFICE_STANDARD_RATE_CLICK = "back.office.standard.rate.click";
	public static final String BACK_OFFICE_INSERT_ROW_OPTION = "back.office.insert.row.option";
	public static final String BACK_OFFICE_TAX_COUNTRYCODE = "back.office.tax.countrycode";
	public static final String BACK_OFFICE_TAX_BILLLINGZIP = "back.office.tax.BillingZip";
	public static final String BACK_OFFICE_TAX_BILLLINGCITY = "back.office.tax.BillingCity";
	public static final String BACK_OFFICE_TAX_RATE = "back.office.tax.Rate";
	public static final String BACK_OFFICE_TAX_SAVE_BUTTON = "back.office.tax.save.button";
	
	//ShippingMethod
	public static final String FRONT_OFFICE_SHIPPING_METHOD = "front.office.shipping.method";
	public static final String FRONT_OFFICE_SHIPPING_FIRST_NAME = "front.office.shipping.first.name";
	public static final String FRONT_OFFICE_SHIPPING_LAST_NAME = "front.office.shipping.last.name";
	public static final String FRONT_OFFICE_SHIPPING_COUNTRY = "front.office.checkout.shipping.country.xpath";
	public static final String FRONT_OFFICE_SHIPPING_COUNTRY_TEXT = "front.office.checkout.shipping.country.text";
	public static final String FRONT_OFFICE_SHIPPING_ADDRESS1 = "front.office.checkout.shipping.address1";
	public static final String FRONT_OFFICE_SHIPPING_CITY = "front.office.checkout.shipping.city";
	public static final String FRONT_OFFICE_SHIPPING_POSTCODE = "front.office.checkout.shipping.postcode";
	

	// visa src
	public static final String FRONT_OFFICE_VISA_SRC_BUTTON = "front.office.visacheckoutbutton";
	public static final String FRONT_OFFICE_VISAEMAIL = "front.office.visaemail.attribute";
	public static final String FRONT_OFFICE_VISAEMAIL_VALUE = "front.office.visaemail.attribute.value";
	public static final String FRONT_OFFICE_VISA_SRC_EMAIL_CONTINUE = "front.office.visa.src.email.continue";
	public static final String FRONT_OFFICE_VISA_SRC_CODE_CONTINUE = "front.office.visa.src.code.continue";
	public static final String BACK_OFFICE_VCOPAYMENT_MANAGE = "back.office.vco.payment.manage";
	public static final String BACK_OFFICE_VCO_ENABLE = "back.office.vco.enable.id";
	public static final String BACK_OFFICE_VCO_TRANSACTION_TYPE = "back.office.vco.transaction.type.id";
	public static final String BACK_OFFICE_VCO_ENVIRONMENT = "back.office.vco.environment.id";
	public static final String BACK_OFFICE_VCO_MERCHANT_ID = "back.office.vco.merchant.xpath";
	public static final String BACK_OFFICE_VCO_MERCHANT_KEY_ID = "back.office.vco.merchant.key.xpath";
	public static final String BACK_OFFICE_VCO_MERCHANT_SECRET_KEY = "back.office.vco.merchant.secret.key.xpath";
	public static final String BACK_OFFICE_VCO_API_KEY = "back.office.vco.api.key.id";
	public static final String BACK_OFFICE_VCO_FM_ENABLE = "back.office.vco.fm.id";
	public static final String BACK_OFFICE_VCO_FM_ENABLE_XPATH = "back.office.vco.fm.xpath";
	public static final String BACK_OFFICE_VCO_DFP = "back.office.vco.dfp.id";
	public static final String BACK_OFFICE_VCO_SAVE_BUTTON = "back.office.vco.save.button.xpath";
	
	

	public static final String FRONT_OFFICE_SEARCH = "front.office.search.id";
	public static final String FRONT_OFFICE_SEARCH_RESULT = "front.office.search.result.xpath";
	public static final String FRONT_OFFICE_QUANTITY = "front.office.quantity.name";
	public static final String FRONT_OFFICE_ADD_TO_CART = "front.office.addToCart.name";
	public static final String FRONT_OFFICE_VIEW_CART = "front.office.viewCart.xpath";
	public static final String FRONT_OFFICE_PROCEED_TO_CHECKOUT_GUESTUSER = "front.office.proceed.guestuser";
	public static final String FRONT_OFFICE_PROCEED_TO_CHECKOUT_REGISTEREDUSER = "front.office.proceed.registereduser";
	public static final String FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD = "front.office.payment.method.cc";
	public static final String FRONT_OFFICE_PAYMENT_METHOD_VISA_CHECKOUT = "front.office.payment.method.vco";

	public static final String FRONT_OFFICE_BILLING_FIRST_NAME = "front.office.checkout.first.name.id";
	public static final String FRONT_OFFICE_BILLING_LAST_NAME = "front.office.checkout.last.name.id";
	public static final String FRONT_OFFICE_BILLING_COUNTRY = "front.office.checkout.country.xpath";
	public static final String FRONT_OFFICE_BILLING_COUNTRY_TEXT="front.office.checkout.billing.country.text";
	public static final String FRONT_OFFICE_DIFFERENT_SHIPPING_ADDRESS_CHECKBOX="front.office.different.shipping.address.checkbox";
	public static final String FRONT_OFFICE_BILLING_COUNTRY_DROPDOWN = "front.office.checkout.country.dropdown.xpath";
	public static final String FRONT_OFFICE_BILLING_STREET = "front.office.checkout.street.id";
	public static final String FRONT_OFFICE_BILLING_STREET2 = "front.office.checkout.street.id2";
	public static final String FRONT_OFFICE_BILLING_CITY = "front.office.checkout.city.id";
	public static final String FRONT_OFFICE_BILLING_STATE = "front.office.checkout.state.id";
	public static final String FRONT_OFFICE_BILLING_ZIP = "front.office.checkout.zip.id";
	public static final String FRONT_OFFICE_BILLING_PHONE_NO = "front.office.checkout.phone.id";
	public static final String FRONT_OFFICE_BILLING_EMAIL = "front.office.checkout.email.id";
	public static final String FRONT_OFFICE_USE_NEW_CARD = "front.office.checkout.use.new.card.xpath";
	public static final String FRONT_OFFICE_EXPIRY_MONTH = "front.office.expiry.month.id";
	public static final String FRONT_OFFICE_EXPIRY_YEAR = "front.office.expiry.year.id";
	public static final String FRONT_OFFICE_USE_NEW_CARD_PAYMENT_METHOD = "wc-isv-unified-checkout-use-new-payment-method";
	public static final String FRONT_OFFICE_CREDIT_CARD_BUTTON="front.office.checkout.credit.card";
	public static final String FRONT_OFFICE_CHECKBOX_SAVE_CARD = "front.office.checkout.save.card.cssselector";
	public static final String FRONT_OFFICE_ADD_NEW_CARD_NUMBER = "front.office.add.new.card.number";
	public static final String FRONT_OFFICE_ADD_NEW_SECURITY_NUMBER = "front.office.add.new.security.number";
	public static final String FRONT_OFFICE_ADD_NEW_EXPIRATION = "front.office.add.new.expiration";
	public static final String FRONT_OFFICE_CHECKOUT_GUESTUSER_PLACE_ORDER_BUTTON = "front.office.checkout.place.order.xpath";
	public static final String FRONT_OFFICE_CHECKOUT_REGISTEREDUSER_PLACE_ORDER_BUTTON ="front.office.checkout.place.order.xpath";
	public static final String FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON = "front.office.checkout.place.order.xpath";
	public static final String FRONT_OFFICE_ORDER_NUMBER = "front.office.order.number.xpath";
	public static final String FRONT_OFFICE_ORDER_CONFIRMATION_TEXT = "Thank you. Your order has been received.";
	public static final String FRONT_OFFICE_SAVED_CARD = "front.office.saved.card";
	public static final String FRONT_OFFICE_SAVED_CARD_CVN = "front.office.saved.card.cvn";
	public static final String FRONT_OFFICE_SECURITYPAGE_HIDEDETAILS_BUTTON_ID="front.Office.Securitypage.hidedetails.button.id";
	public static final String FRONT_OFFICE_SECURITYPAGE_FOLINK_ID="front.Office.Securitypage.FOlink.id";
	
	//NULL VALUES
	public static final String FRONT_OFFICE_NULL_VALUE_ERROR_MESSAGE = "front.office.null.value.error.messsage";
	
	
	//Unified Checkout-Credit Card
	public static final String FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER = "front.office.add.new.uc.card.number";
	public static final String FRONT_OFFICE_ADD_NEW_UC_SECURITY_NUMBER ="front.office.add.new.cc.security.number";
	public static final String FRONT_OFFICE_ADD_NEW_EXPIRATION_MONTH = "front.office.add.new.expiration.month";
	public static final String FRONT_OFFICE_ADD_NEW_EXPIRATION_YEAR = "front.office.add.new.expiration.year";
	public static final String FRONT_OFFICE_ADD_NEW_CONFIRMORDER_BUTTON = "front.office.add.new.confirmorder.button";
	public static final String MCE_ID = "mce";
	public static final String FRONT_OFFICE_PAYMENT_METHOD_UC_CREDIT_CARD = "front.office.payment.method.uc.cc";
	public static final String FRONT_OFFICE_PHONE_NUMBER = "9876543210";
	public static final String FRONT_OFFICE_MYACCOUNT_UC_ADD_CARD ="front.office.myaccount.uc.addcard";
	public static final	CharSequence FRONT_OFFICE_NULL_INVALID_ERROR_MESSAGE_TEXT= "front.office.null.invalid.error.message";
	//GooglePay
	
	public static final String FRONT_OFFICE_GPAY_BUTTON="front.office.checkout.gpay.xpath";
	public static final String FRONT_OFFICE_GPAY_IFRAMEPAY_BUTTON="front.Office.Gpaypay.iframe.button.xpath";
	public static final String FRONT_OFFICE_GPAY_EMAIL_BOX = "front.office.gpay.email.box";
	public static final String FRONT_OFFICE_GPAY_EMAIL = "testingwithgpay@gmail.com";
	public static final String FRONT_OFFICE_GPAY_PASSWORD_BOX = "front.office.gpay.password.box";
	public static final String FRONT_OFFICE_GPAY_PASSWORD = "test@789";
	public static final String FRONT_OFFICE_GPAY_PASSWORD_NEXTBUTTON = "front.office.gpay.password.nextbutton";
	public static final String FRONT_OFFICE_GPAY_EMAIL_NEXTBUTTON = "front.office.gpay.email.nextbutton";
	public static final String FRONT_OFFICE_PAYMENT_METHOD_GPAY_BUTTON="front.Office.Gpay.button.xpath";
	public static final String BACK_OFFICE_ORDERS_GPAY_TRANSACTION_TYPE = "back.office.gpay.transaction.type.id";
	public static final String BACK_OFFICE_GPAYPAYMENT_MANAGE="back.office.gpay.payment.manage";
	public static final String BACK_OFFICE_GPAY_ENABLE = "back.office.gpay.enable.id";
	public static final String BACK_OFFICE_GPAY_ENVIRONMENT = "back.office.gpay.environment.id";
	public static final String BACK_OFFICE_GPAY_MERCHANT_ID = "back.office.gpay.merchant.xpath";
	public static final String BACK_OFFICE_GPAY_MERCHANT_KEY_ID= "back.office.gpay.merchant.key.id.xpath";
	public static final String BACK_OFFICE_GPAY_MERCHANT_SECRET_KEY= "back.office.gpay.merchant.secret.key.xpath";
	public static final String BACK_OFFICE_GPAY_FM_ENABLE = "back.office.gpay.fm.id";
	public static final String BACK_OFFICE_GPAY_FM_ENABLE_XPATH = "back.office.gpay.fm.xpath";
	public static final String BACK_OFFICE_GPAY_DFP = "back.office.gpay.dfp.id";
	public static final String BACK_OFFICE_GPAY_SAVE_BUTTON = "back.office.gpay.save.button.xpath";
	
	
	public static final String USER_INDEX = "userIndex";
    public static final String PROFILE_ID = "profileId";
    public static final String BROWSER_MAX_TIMEOUT_SECONDS = "browser.max.timeout.seconds";
    public static final String SCREENSHOTPATH = "/resources/screenshot/";
    public static final String FAILEDSCREENSHOTPATH = "/resources/failedScreenshot/";
    public static final String SUCCESSSCREENSHOTPATH = "/resources/successScreenshot/";
    

	public static final String NULL = "null";
	public static final String INVALID = "invalid";
	public static final String PASS = " Success";

	public static final String TABLE = "table";
	public static final String HIPHEN = "-";
	public static final String COLON = ":";
	public static final String OPEN_PARENTHESIS = ")";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String QUESTION_MARK = "?";
	public static final String DISABLE = "Disable";
	public static final String ENABLE = "Enable";
	public static final String NEW_CARD = "New Card";
	public static final String SAVED_CARD = "saved-card";
	public static final String ORDER_NUMBER = "Order Number :";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CHARGE = "Charge";
	public static final String AUTHORIZE = "Authorize";
	public static final String CAPTURE = "Capture";
	public static final String REFUND = "Refund";
	public static final String PARTIALREFUND = "PartialRefund";
	public static final String VOID = "Void";

	public static final String SALE_DECISION_MANAGER = "SaleDecisionManager";
	public static final String AUTH_DECISION_MANAGER = "DecisionManager";
	
	public static final String AUTH_REVERSAL = "AuthReversal";
	public static final String PARTIAL_CAPTURE = "PartialCapture";
	public static final String PARTIAL_VOID_CAPTURE = "PartialVoidCapture";
	public static final String PARTIAL_VOID_REFUND = "PartialVoidRefund";
	public static final String PARTIAL_REFUND = "PartialRefund";
	public static final String NEW_CARD_TOKEN = "NewCardToken";
	public static final String TOKEN_NEW_CARD = "TokenNewCard";
	public static final String UPDATE_CARD_TOKEN = "UpdateCardToken";
	public static final String DELETE_CARD_TOKEN = "DeleteCardToken";
	public static final String TOKEN_STORED_CARD = "TokenStoredCard";
	public static final String NEW_CARD_TOKEN_DETAILS = "NewCardTokenDetails";
	public static final String UPDATE_CARD_TOKEN_DETAILS = "UpdateCardTokenDetails";
	public static final String UPDATE_CARD_TOKEN_BEFORE = "UpdateCardToken Before";
	public static final String UPDATE_CARD_TOKEN_AFTER = "UpdateCardToken After";
	public static final String DELETE_CARD_TOKEN_BEFORE = "DeleteCardToken Before";
	public static final String DELETE_CARD_TOKEN_AFTER = "DeleteCardToken After";

	public static final String EBC_ORGANIZATION_ID_ATTRIBUTE = "ebc.organization.id.attribute";
	public static final String EBC_ORGANIZATION_ID_ATTRIBUTE_VALUE = "ebc.organization.id.attribute.value";
	public static final String EBC_USERNAME_ATTRIBUTE = "ebc.username.attribute";
	public static final String EBC_USERNAME_ATTRIBUTE_VALUE = "ebc.username.attribute.value";
	public static final String EBC_PASSWORD_ATTRIBUTE = "ebc.password.attribute";
	public static final String EBC_PASSWORD_ATTRIBUTE_VALUE = "ebc.password.attribute.value";
	public static final String EBC_LOGIN_SUBMIT_BUTTON = "ebc.login.submit.button";
	public static final String EBC_LOGIN_URL = "ebc.login.url";
	public static final String EBC_TRANSACTION_MANAGEMENT_ICON = "ebc.transaction.management.icon";
	public static final String EBC_DECISION_MANAGER_ICON = "ebc.decision.manager.icon";
	public static final String EBC_DECISON_MANAGER_CASES = "ebc.decision.manager.cases";
	public static final String EBC_DECISON_MANAGER_CASES_SEARCH = "ebc.decision.manager.cases.search";
	public static final String EBC_DECISION_MANAGER_ACCEPT = "ebc.decision.manager.cases.accept";
	public static final String EBC_DECISION_MANAGER_REJECT = "ebc.decision.manager.cases.reject";
	public static final String EBC_DECISION_MANAGER_COMMENTS = "ebc.decision.manager.cases.comments";
	public static final String EBC_DECISION_MANAGER_SUBMIT_BUTTON = "ebc.decision.manager.cases.submit.button";
	public static final String EBC_TRANSACTION_MANAGEMENT = "ebc.transaction.management";
	public static final String EBC_TRANSACTION_MANAGEMENT_TRANSACTIONS = "ebc.transaction.management.transactions";
	public static final String EBC_TRANSACTION_SIDEBAR = "ebc.transaction.sidebar";
	public static final String EBC_TRANSACTION_REQUESTID = "ebc.transaction.requestid.quicksearch";
	public static final String EBC_TRANSACTION_QUICKSEARCH_BUTTON="ebc.transaction.quicksearch.button";
	public static final String EBC_TRANSACTION_REQUESTID_AFTERSEARCH="ebc.transaction.requestid.aftersearch";
	public static final String EBC_TRANSACTION_SEARCHRESULT = "ebc.transaction.requestid.searchResult";
	public static final String EBC_LOGOUT = "ebc.logout";
	public static final String EBC_LOGOUT_BUTTON = "ebc.logout.button";
	public static final String ORDER_REFERENCE_NUMBER_COLON = " OrderReferenceNumber : ";
	public static final String TRANSACTION_ID_COLON = "TransactionID :";
	public static final String CART_DETAILS = "CartDetails";
	public static final String EBC_LINEITEMS = "ebc.lineItemTab";
	public static final String EBC_CUSTOMER_INFO = "ebc.customerDetailsTab";
	public static final String EBC_PAYMENT_INFO = "ebc.paymentInformationTab";
	public static final String EBC_LOGGED_IN_MERCHANT_ID = "ebc.loggedIn.merchantID";
	public static final String EBC_AVS_CODE = "ebc.avs.code";
	public static final String EBC_CVN_CODE = "ebc.cvn.code";
	public static final String EBC_REASON_CODE = "ebc.reason.code";
	public static final String EBC_CUSTOMER_SHIPPING_ADDRESS = "ebc.customer.information.shippingAddress";
	public static final String EBC_CUSTOMER_SHIPPING_CITY = "ebc.customer.information.shippingCity";
	public static final String EBC_CUSTOMER_SHIPPING_STATE = "ebc.customer.information.shippingState";
	public static final String EBC_CUSTOMER_SHIPPING_ZIPCODE = "ebc.customer.information.shippingZipCode";
	public static final String EBC_CUSTOMER_SHIPPING_COUNTRY = "ebc.customer.information.shippingCountry";
	
	public static final String EBC_CUSTOMER_BILLING_ADDRESS = "ebc.customer.information.billingAddress";
	public static final String EBC_CUSTOMER_BILLING_CITY = "ebc.customer.information.billingCity";
	public static final String EBC_CUSTOMER_BILLING_STATE = "ebc.customer.information.billingState";
	public static final String EBC_CUSTOMER_BILLING_ZIPCODE = "ebc.customer.information.billingZipCode";
	public static final String EBC_CUSTOMER_BILLING_COUNTRY = "ebc.customer.information.billingCountry";
	
	public static final String EBC_CUSTOMER_FIRSTNAME = "ebc.customer.information.firstName";
	public static final String EBC_CUSTOMER_LASTNAME = "ebc.customer.information.lastName";
	public static final String EBC_PARTNER_SOLUTION_ID = "ebc.partner.solution.id";
	public static final String EBC_PAYMENT_INSTRUMENT_EXPIRATION_DATE = "ebc.payment.instrument.expiration.date";
	public static final String EBC_PAYMENT_INSTRUMENT_ACCOUNT_SUFFIX = "ebc.payment.instrument.account.suffix";
	public static final String EBC_REQUEST_INFORMATION_APPLICATIONS = "ebc.request.information.applications";
	public static final String CYBS_EBCCHECK_LINEITEM = "CYBS_EBCCheck_LineItem";
	public static final String CYBS_EBCCHECK_TRANSACTION_HISTORY = "cybs.ebcCheck.transaction.history";
	public static final String CYBS_EBCCHECK_CUSTOMER_INFO = "CYBS_EBCCheck_CustomerInfo";
	public static final String CYBS_EBCCHECK_PAYMENT_INFO = "CYBS_EBCCheck_PaymentInfo";
	public static final String CUSTOMER_SHIPPING_ADDRESS_DETAILS_ENTRY = "CustomerShippingAddressDetailsEntry";
	public static final String CUSTOMER_INVOICE_ADDRESS_DETAILS_ENTRY = "CustomerInvoiceAddressDetailsEntry";
	public static final String MYACCOUNT_ADDRESS_DETAILS_ENTRY = "MyAccountAddressDetailsEntry";
	public static final String ITEM_DETAILS_ENTRY = "ItemDetailsEntry";
	public static final String TRANSACTION_DETAIL = "TransactionDetail";
	public static final String CUSTOMER_DETAILS_ERROR_MESSAGE = "CustomerDeatils Error message";
	public static final String CARD_DETAILS = "CardDetails";
	public static final String ORDER_CONFIRMATION_PAGE = "OrderConfirmationPage";
	public static final String TRANSACTION_STATUS = "TransactionStatus";
	public static final String CAPTURE_TRANSACTION_STATUS = "CaptureTransactionStatus";
	public static final String REFUND_TRANSACTION_STATUS = "RefundTransactionStatus";
	public static final String VOID_TRANSACTION_STATUS = "VoidTransactionStatus";
	public static final String PARTNER_SOLUTION_ID = "LBV6WYMD";
	public static final String CREDIT_CARD_SETTLEMENT = "Credit Card Settlement";
	public static final String CREDIT_CARD_AUTHORIZATION = "Credit Card Authorization";
	public static final String DECISION_MANAGER = "Decision Manager";
	public static final String CREDIT_CARD_CREDIT = "Credit Card Credit";
	public static final String VOIDED_TRANSACTIONS = "Voided Transactions";
	public static final String SUBSCRIPTION_CREATION = "Subscription Creation";
    public static final String PAYERAUTH= "PayerAuth";
	public static final String PAYER_AUTHENTICATION = "Payer Authentication Validation";
	public static final String THREE_DS1 = "3DS1";
	public static final String THREE_DS2 = "3DS2";
	public static final String VALIDATION_MESSAGE = "validationMessage";
	// public static final String SCROLL_TO_BOTTOM_OF_THE_PAGE="";
	public static final String TECH_WOOCOMMERCE = "woocommerce";

	public static final String Shop="//*[@id='modal-1-content']/ul/li[5]/a";
	public static final String product="//*[@id='main']/ul/li[1]/a[1]/h2";
	public static final String SALE = "Sale";
	public static final String AUTH = "Auth";
	public static final String SALEDECISIONMANAGER = "SaleDecisionManager";
	public static final String DM_AUTH = "DM_Auth";
	public static final String DM_SALE = "DM_Sale";
	public static final String YES = "YES";
	public static final String NO = "No";
	public static final String TEST_SCENARIO = "testscenario";
	public static final String SERVICE_TYPE = "serviceType";
	public static final String POSITIVE = "positive";
	public static final String NEGATIVE = "negative";
	public static final String NEGATIVE_ITEM_DATA = "NegativeItemData";
	public static final String HASH = "#";
	public static final String ACCEPT = "Accept";
	public static final String PREFIX = "prefix";
	public static final String EMPTY_STRING = "";

	public static final String UNDERSCORE = "_";
	public static final boolean TRUE = true;
	public static final Long QUANTITY = 99999999998L;
	public static final String TR = "tr";
	public static final String TD = "td";
	public static final String TH = "th";
	public static final String DOLLAR = "$";
	public static final String EBC = "ebc";
	public static final String COMMA = ",";

	public static final int SIXTY_SECOND = 60;
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	
	public static final String PAYER_AUTH_ONE = "1";
	public static final String PAYER_AUTH_TWO = "2";
	public static final String PAYER_AUTH_THREE = "3";
	public static final String PAYER_AUTH_FOUR = "4";
	public static final String HUNDRED = "100";
	public static final String _SHEET_NAME = ".sheet.name";
	public static final String GET_DATA = "getData";

	public static final String PREIX_EBC_TRANSACTIONS_URL = "preix.ebc.transactions.url";
	public static final String SUFFIX_EBC_TRANSACTIONS_URL = "suffix.ebc.transactions.url";

	public static final String WOOCOMMERCE_AUTOMATION_TESTING = "WooCommerce Automation Testing";
//	public static final String ADD_PROFILE = "Add Profile";
	public static final String PROFILE_TABLE = "profile.table";
	public static final String SCROLL_TO_BOTTOM_OF_THE_PAGE = "window.scrollTo(0,350)";
	public static final String SCROLL_TO_THREE_FOURTH_OF_THE_PAGE = "window.scrollTo(0,1250)";

	public static final String DOUBLE_BACKWARD_SLASH = "\\";
	public static final String SCREENSHOT_ENABLED = "screenshot.enabled";
	public static final String BASE_PATH_MULTISCREENSHOT_DIRECTORY = "base.path.multiscreenshot.directory";
	public static final String _JPG = ".jpg";
	public static final String USER_DIR = "user.dir";
	public static final String FAILED_SCREENSHOTS_DIRECTORY = "failed.screenshots.directory";
	public static final String FAILED = " failed";
	public static final String FINISHED = " finished";
	public static final String STARTS = " starts";
	public static final String ENDS = " ends";
	public static final String SCREENSHOT_FEATURE_DISABLED = "Screenshot feature disabled";
	public static final String FAILED_SCREENSHOT = "Failed screenshot: ";
	public static final String IOEXCEPTION_OCCURRED = "IOException occurred";
	public static final String TEST_OUTPUT_DIRECTORY = "test.output.directory";
	public static final String A_HREF_EQUAL = "<a href='";
	public static final String IMG_SRC_EQUAL = "<img src='";
	public static final String SINGLE_QUOTE_CLOSING_TAG = "'>";
	public static final String FORWARD_SLASH_CLOSING_TAG = "/>";
	public static final String SCREENSHOT_HEIGHT_WIDTH = "screenshot.height.width";

	public static final String DOT = ".";
	public static final String SINGLE_QUOTATION = "'";
	public static final String CHECKBOX_HIPHEN_HIPHEN = "checkbox--";
	public static final String INNERHTML = "innerHTML";
	public static final String CLOSING_A_TAG = "</a>";
	public static final String ARGUMENTS_SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true)";
	public static final String SPACE = " ";
	public static final int THIRTY = 30;
	public static final String ORG_ID = "orgId";

	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String EDGE = "edge";
	public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	public static final String FIREFOXDRIVER_PATH = "firefoxdriver.path";
	public static final String CHROMEDRIVER_PATH = "chromedriver.path";
	public static final String EDGEDRIVER_PATH = "edgedriver.path";
	public static final String EBC_PROFILE_DATA_SHEET_PREFIX = "EBCProfileDataSheetPrefix";
	public static final String NETSUITE_PROFILE_DATA_SHEET_PREFIX = "EBCProfileDataSheetPrefix";
	public static final String PPP_DATA_SHEET_PREFIX = "pppDataSheetPrefix";
	public static final String CUSTOM_ROLE_URL = "custom.role.url";
	public static final String SETUP_WEBSITE_URL = "custom.role.url";
	public static final String SCROLL_TO_TOP_OF_THE_PAGE = "window.scrollTo(0, 0)";
	public static final String KEY_A = "a";
	public static final String VALUE = "value";
	public static final String PENDING_APPROVAL = "Pending Approval";
	public static final String NO_MATCHES = "No matches";
	public static final String NO_MATCHING_FOUND = "No matching found";
	public static final String RESOURCES_FORWARD_SLASH = "resources/";
	public static final String CONFIG_PROPERTIES = "config.properties";
	public static final String CONFIG_FILE_EQUAL = "config file=";
	public static final String ASSIGNED_WOOCOMMERCE_SPECIFIC_XLSX = "Assigned woocommerce specific XLSX";
	public static final String FILENAME_EQUAL = "fileName=";
	public static final String SHEETNAME_EQUAL = "fileName=";
	public static final String ROW_COUNT_EQUAL = "row count=";
	public static final String COL_COUNT_EQUAL = "row count=";
	public static final String COMMA_SPACE = ", ";

	public static final String ROW_EQUAL = "Row=";
	public static final String ERROR_DELETING_TEST_OUTPUT = "Error deleting test-output";
	public static final String TEST_OUTPUT_DELETED = "test-output deleted";
	public static final String TEST_OUTPUT_DELETE_IS_CALLED = "The test-output delete is called";
	public static final String FRONT_OFFICE_UC_BILLING_EMAIL = "front.office.uc.billing.email";
	public static final String FRONT_OFFICE_GOOGLE_SIGNIN_URL = "front.office.google.signin.url";
}
	
	 
