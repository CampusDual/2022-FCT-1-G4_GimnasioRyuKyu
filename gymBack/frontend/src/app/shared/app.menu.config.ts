import {
  MenuRootItem,

} from 'ontimize-web-ngx';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'calendar', name: 'calendar', icon: 'calendar_month', route: '/main/calendar' },
  { id: 'clients', name: 'clients', icon: 'people', route: '/main/clients' },
  { id: 'monitors', name: 'monitors', icon: 'self_improvement', route: '/main/monitors' },
  { id: 'classes', name: 'classes', icon: 'class', route: '/main/classes' },
  { id: 'rooms', name: 'rooms', icon: 'room', route: '/main/rooms' },
  { id: 'subscriptions', name: 'subscriptions', icon: 'payment', route: '/main/subscriptions' },
  { id: 'assign-classes', name: 'assing classes', icon: 'room_preferences', route: '/main/assign-classes' },
  // { id: 'assign-clients', name: 'assing clients', icon: 'manage_accounts', route: '/main/assign-clients' }
];
