// Backend API base url
export const BACKEND_API_URL = "http://127.0.0.1:8080/api"

// Purse mappings
export const PURSE = BACKEND_API_URL + "/purses"

// Events mappings
export const EVENTS = BACKEND_API_URL + "/events"
export const EVENTS_LIST_BY_PURSE = EVENTS + '/purse/'

// Currencies mappings
export const CURRENCIES = BACKEND_API_URL + "/currencies"
export const CURRENCIES_UPDATE = BACKEND_API_URL + "/currencies/refresh"

// Role mappings
export const ROLES = BACKEND_API_URL + "/roles";