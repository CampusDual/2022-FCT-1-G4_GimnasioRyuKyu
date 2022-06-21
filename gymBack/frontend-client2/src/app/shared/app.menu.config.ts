import {
  MenuRootItem,

} from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'calendar', name: 'calendar', icon: 'calendar_month', route: '/main/calendar' },
  { id: 'logout', name: 'LOGOUT', route: '/main/login', icon: 'power_settings_new', confirm: 'yes' }

];
