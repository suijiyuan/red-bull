# Red-Bull

Just for fun.

## Environment

| software | version      |
|----------|--------------|
| CentOS   | 9 Stream x64 |
| OpenJDK  | 11.0.15+10   |
| Maven    | 3.8.4        |
| git      | 2.37.3       |

## How to install it

```shell
# run as root
# update system
dnf -y update

# change ssh port from 22 to 2222
firewall-cmd --zone=public --add-port=2222/tcp --permanent
firewall-cmd --reload
semanage port -a -t ssh_port_t -p tcp 2222

vi /etc/ssh/sshd_config
# modify ssh port into it
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

## How to use it

```shell
cd ~
vi .bash_profile
# add $SALT: jasypt.encryptor.password into it

git clone git@github.com:suijiyuan/red-bull.git
cd red-bull
vi src/main/resources/prod/application-prod.yml
# modify the configuration information starting with "ENC", pay attention to use jasypt.encryptor.password

# mvn jasypt:encrypt-value -Djasypt.encryptor.password="<jasyptEncryptorPassword>" 
# -Djasypt.plugin.value="<theValueYouWantToEncrypt>"

# mvn jasypt:decrypt -DjasyptEncryptorPassword="<jasyptEncryptorPassword>" 
# -DencryptedValue="<theValueYouWantToDecrypt>"

mvn clean package -P prod

cd target/bin
sh start.sh
tail -f ~/logs/red-bull/red-bull.log
```