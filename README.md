# Red-Bull

Just for fun.

## Environment

| Software         | Version            |
|------------------|--------------------|
| Operation System | Rockey Linux 9 x64 |
| OpenJDK          | 11.0.15+10         |
| Maven            | 3.8.4              |
| Git              | 2.37.3             |

## How to install it

```shell
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