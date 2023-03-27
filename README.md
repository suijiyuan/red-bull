# red-bull

Just for fun.

## environment

| software | version      |
|----------|--------------|
| CentOS   | 9 Stream x64 |
| OpenJDK  | 11.0.15+10   |
| Maven    | 3.8.4        |
| git      | 2.37.3       |

## how to install it

```shell
# run as root
# update system
dnf -y update

# change ssh port from 22 to 2222
firewall-cmd --zone=public --add-port=2222/tcp --permanent
firewall-cmd --reload
semanage port -a -t ssh_port_t -p tcp 2222

vi /etc/ssh/sshd_config
systemctl restart sshd

# add a user and set sudo permissions
useradd admin
passwd admin
usermod -aG wheel admin

# run as a user who in wheel group
su - admin

# install software
sudo dnf -y install java-11-openjdk-devel
sudo dnf -y install git
sudo dnf -y install maven
sudo dnf -y install nginx
sudo dnf -y install mariadb-server

# automatically start on reboot
sudo systemctl start nginx
sudo systemctl enable nginx
sudo systemctl start mariadb
sudo systemctl enable mariadb

# setup software
# mariadb
sudo mysql_secure_installation
mysql -u root -p
CREATE USER 'admin'@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
FLUSH PRIVILEGES;
```

## how to use it